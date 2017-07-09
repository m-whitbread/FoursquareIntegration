package uk.co.markomeara.whitbread.data.source;

import io.reactivex.Maybe;
import retrofit2.http.GET;
import retrofit2.http.Query;
import uk.co.markomeara.whitbread.data.ResponseWrapper;
import uk.co.markomeara.whitbread.data.VenueItem;

public interface FoursquareApi {

    @GET("venues/explore")
    Maybe<ResponseWrapper<VenueItem>> getVenues(@Query("near") String location);
}
