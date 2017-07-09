package uk.co.markomeara.whitbread.venuesearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import uk.co.markomeara.whitbread.R;
import uk.co.markomeara.whitbread.data.source.FoursquareApi;
import uk.co.markomeara.whitbread.di.Injector;
import uk.co.markomeara.whitbread.util.ActivityUtils;

public class VenueSearchActivity extends AppCompatActivity {

    @Inject
    FoursquareApi foursquareApi;

    private VenueSearchContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.venuesearch_activity);
        Injector.get().inject(this);

        VenueSearchFragment venueSearchFragment = (VenueSearchFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (venueSearchFragment == null) {
            // Create the fragment
            venueSearchFragment = VenueSearchFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), venueSearchFragment, R.id.contentFrame);
        }

        presenter = new VenueSearchPresenter(venueSearchFragment, foursquareApi);
    }
}