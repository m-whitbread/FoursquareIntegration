package uk.co.markomeara.whitbread.venuesearch;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Maybe;
import uk.co.markomeara.whitbread.data.ResponseWrapper;
import uk.co.markomeara.whitbread.data.VenueItem;
import uk.co.markomeara.whitbread.data.source.FoursquareApi;
import uk.co.markomeara.whitbread.testrule.RxSchedulersTestRule;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static uk.co.markomeara.whitbread.util.ApiContentHelper.generateVenueItemList;
import static uk.co.markomeara.whitbread.util.ApiContentHelper.wrapVenueItemList;

public class VenueSearchPresenterTest {

    @Rule
    public RxSchedulersTestRule rxSchedulersTestRule = new RxSchedulersTestRule();

    @Mock
    VenueSearchContract.View venueSearchView;

    @Mock
    FoursquareApi foursquareApi;

    private VenueSearchPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new VenueSearchPresenter(venueSearchView, foursquareApi);
    }

    @Test
    public void testVenueListSentToViewWhenReceived() {
        List<VenueItem> venueItemListReturned = generateVenueItemList();
        ResponseWrapper<VenueItem> successResponse = wrapVenueItemList(venueItemListReturned);
        Maybe<ResponseWrapper<VenueItem>> venueListObservable = Maybe.just(successResponse);

        when(foursquareApi.getVenues(anyString())).thenReturn(venueListObservable);

        presenter.searchVenues("");

        verify(venueSearchView).displayVenueList(eq(venueItemListReturned));
    }

    @Test
    public void testProgressBarShownWhenLoadingVenues() {
        when(foursquareApi.getVenues(anyString())).thenReturn(Maybe.empty());

        presenter.searchVenues("");

        verify(venueSearchView).showProgressBar();
    }

    @Test
    public void testProgressBarHiddenAfterRequestError() {
        when(foursquareApi.getVenues(anyString())).thenReturn(Maybe.error(new Exception()));

        presenter.searchVenues("");

        verify(venueSearchView).hideProgressBar();
    }

    @Test
    public void testErrorShownAfterNetworkError() {
        when(foursquareApi.getVenues(anyString())).thenReturn(Maybe.error(new Exception()));

        presenter.searchVenues("");

        verify(venueSearchView).displayRequestError(anyString());
    }

    @Test
    public void testProgressBarHiddenAfterSuccessfulRequest() {
        List<VenueItem> venueItemList = generateVenueItemList();
        ResponseWrapper<VenueItem> successResponse = wrapVenueItemList(venueItemList);

        when(foursquareApi.getVenues(anyString())).thenReturn(Maybe.just(successResponse));

        presenter.searchVenues("");

        verify(venueSearchView).hideProgressBar();
    }

}
