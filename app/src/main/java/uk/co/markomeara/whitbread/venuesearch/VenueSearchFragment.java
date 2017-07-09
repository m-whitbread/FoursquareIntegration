package uk.co.markomeara.whitbread.venuesearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.markomeara.whitbread.R;
import uk.co.markomeara.whitbread.data.VenueItem;
import uk.co.markomeara.whitbread.venuesearch.adapters.VenueListAdapter;

public class VenueSearchFragment extends Fragment implements VenueSearchContract.View {

    @BindView(R.id.venue_list)
    RecyclerView venueListView;

    @BindView(R.id.list_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.search)
    Button searchButton;

    @BindView(R.id.location_edittext)
    EditText locationField;

    private Unbinder viewUnbinder;
    private VenueSearchContract.Presenter presenter;
    private VenueListAdapter listAdapter = new VenueListAdapter();

    public static VenueSearchFragment newInstance() {
        return new VenueSearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.venuesearch_fragment, container, false);
        viewUnbinder = ButterKnife.bind(this, view);
        setUpList();
        setupListeners();
        return view;
    }

    private void setUpList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        venueListView.setLayoutManager(layoutManager);
        venueListView.setAdapter(listAdapter);
        venueListView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(venueListView.getContext(),
                layoutManager.getOrientation());
        venueListView.addItemDecoration(dividerItemDecoration);
    }

    private void setupListeners() {
        searchButton.setOnClickListener(v -> {
            String location = locationField.getText().toString();
            presenter.searchVenues(location);
        });
    }

    @Override
    public void onStop() {
        presenter.stop();
        super.onStop();
    }

    @Override
    public void displayVenueList(List<VenueItem> venueList) {
        listAdapter.updateVenueList(venueList);
    }

    @Override
    public void setPresenter(VenueSearchPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayRequestError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewUnbinder.unbind();
    }
}
