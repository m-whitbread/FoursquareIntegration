package uk.co.markomeara.whitbread.venuesearch;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import uk.co.markomeara.whitbread.R;
import uk.co.markomeara.whitbread.data.Venue;
import uk.co.markomeara.whitbread.testrule.MockServerTestRule;
import uk.co.markomeara.whitbread.util.ApiContentHelper;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class VenueListUiTest {

    @Rule
    public IntentsTestRule<VenueSearchActivity> activityRule =
            new IntentsTestRule<>(VenueSearchActivity.class);

    @Rule
    public MockServerTestRule mockServerTestRule = new MockServerTestRule();

    private List<Venue> simpleVenueList;

    @Before
    public void setup() {
        simpleVenueList = ApiContentHelper.generateSimpleVenueList();
        Espresso.registerIdlingResources(mockServerTestRule.getOkHttpIdlingResource());
    }

    @Test
    public void testLocationFieldDisplayed() {
        onView(withId(R.id.location_edittext)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void testSearchButtonDisplayed() {
        onView(withId(R.id.search)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void testVenueIsDisplayedAfterSearch() {
        mockServerTestRule.enqueueSuccess(ApiContentHelper.wrapVenueList(simpleVenueList));

        onView(withId(R.id.location_edittext)).perform(typeText("Westminster, London"));
        onView(withId(R.id.search)).perform(click());

        onView(allOf(withId(R.id.venue_name), withText(simpleVenueList.get(0).getName())))
                .check(matches(isCompletelyDisplayed()));

    }

    @Test
    public void testAppTitleIsDisplayed() {
        onView(withText(R.string.app_name)).check(matches(isCompletelyDisplayed()));
    }

}