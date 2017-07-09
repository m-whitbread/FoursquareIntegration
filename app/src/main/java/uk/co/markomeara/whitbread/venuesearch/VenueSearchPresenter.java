package uk.co.markomeara.whitbread.venuesearch;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import uk.co.markomeara.whitbread.data.source.FoursquareApi;

import static dagger.internal.Preconditions.checkNotNull;

public class VenueSearchPresenter implements VenueSearchContract.Presenter {

    private VenueSearchContract.View view;
    private FoursquareApi foursquareApi;
    private CompositeDisposable disposables = new CompositeDisposable();

    public VenueSearchPresenter(VenueSearchContract.View contactListView, FoursquareApi foursquareApi) {
        this.view = checkNotNull(contactListView, "ContactListView cannot be null");
        this.foursquareApi = checkNotNull(foursquareApi, "FoursquareApi cannot be null");
        view.setPresenter(this);
    }

    @Override
    public void searchVenues(String location) {
        view.showProgressBar();

        Disposable requestDisposable = foursquareApi.getVenues(location)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(responseWrapper -> responseWrapper.getGroupResponse().getGroups().get(0).getItems())
                .subscribe(
                        venueList -> {
                            view.displayVenueList(venueList);
                            view.hideProgressBar();
                        },
                        error -> {
                            view.displayRequestError(error.getMessage());
                            view.hideProgressBar();
                        }
                );

        disposables.add(requestDisposable);
    }

    @Override
    public void stop() {
        disposables.clear();
    }
}
