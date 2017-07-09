package uk.co.markomeara.whitbread.venuesearch.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.markomeara.whitbread.R;
import uk.co.markomeara.whitbread.data.Category;
import uk.co.markomeara.whitbread.data.Location;
import uk.co.markomeara.whitbread.data.VenueIcon;
import uk.co.markomeara.whitbread.data.VenueItem;

public class VenueListAdapter extends RecyclerView.Adapter<VenueListAdapter.VenueViewHolder> {

    private static final String ICON_SIZE = "64";

    private List<VenueItem> venueList = new ArrayList<>();

    public void updateVenueList(List<VenueItem> venues) {
        venueList = venues;
        notifyDataSetChanged();
    }

    @Override
    public VenueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.venuesearch_list_item, parent, false);
        return new VenueViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VenueViewHolder holder, int position) {
        holder.setVenueName(venueList.get(position).getVenue().getName());

        List<Category> categories = venueList.get(position).getVenue().getCategories();
        if (categories != null && !categories.isEmpty()) {
            holder.setVenueIcon(categories.get(0).getIcon());
        }

        holder.setVenueAddress(venueList.get(position).getVenue().getLocation());
    }

    @Override
    public int getItemCount() {
        return venueList.size();
    }

    public static class VenueViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.venue_name)
        TextView titleView;
        @BindView(R.id.venue_icon)
        ImageView venueImageView;
        @BindView(R.id.venue_address)
        TextView venueAddress;

        private View itemView;

        VenueViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }

        void setVenueName(String title) {
            titleView.setText(title);
        }

        void setVenueIcon(VenueIcon venueIcon) {

            String iconUrl = venueIcon.getPrefix() + ICON_SIZE + venueIcon.getSuffix();

            Picasso.with(itemView.getContext())
                    .load(iconUrl)
                    .into(venueImageView);
        }

        void setVenueAddress(Location address) {
            List<String> addressLines = address.getFormattedAddress();

            StringBuilder formattedAddress = new StringBuilder();
            for(String line : addressLines) {
                if (formattedAddress.length() > 0) {
                    formattedAddress.append("\n");
                }
                formattedAddress.append(line);
            }

            venueAddress.setText(formattedAddress.toString());
        }
    }
}
