package uk.co.markomeara.whitbread.venuesearch;

import java.util.List;

import uk.co.markomeara.whitbread.data.VenueItem;

public interface VenueSearchContract {

    interface View {
        void displayVenueList(List<VenueItem> venueList);
        void setPresenter(VenueSearchPresenter presenter);
        void displayRequestError(String errorMessage);
        void hideProgressBar();
        void showProgressBar();
    }

    interface Presenter {
        void searchVenues(String location);
        void stop();
    }
}
