package uk.co.markomeara.whitbread.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uk.co.markomeara.whitbread.data.Group;
import uk.co.markomeara.whitbread.data.GroupResponse;
import uk.co.markomeara.whitbread.data.Location;
import uk.co.markomeara.whitbread.data.ResponseWrapper;
import uk.co.markomeara.whitbread.data.Venue;
import uk.co.markomeara.whitbread.data.VenueItem;

public class ApiContentHelper {

    private ApiContentHelper() {
        throw new AssertionError();
    }

    public static List<Venue> generateSimpleVenueList() {
        List<Venue> venueList = new ArrayList<>();

        Venue venue1 = new Venue();
        venue1.setName("Houses of Parliament");
        Location location1 = new Location();
        List<String>  formattedAddress1 = Arrays.asList("Westminster", "London SW1A 0AA");
        location1.setFormattedAddress(formattedAddress1);
        venue1.setLocation(location1);

        venueList.add(venue1);
        return venueList;
    }

    public static List<VenueItem> generateVenueItemList() {
        List<Venue> venueList = generateSimpleVenueList();
        return wrapVenueListAsItems(venueList);
    }

    public static ResponseWrapper<VenueItem> wrapVenueItemList(List<VenueItem> venueItemList) {
        Group<VenueItem> group = new Group<>();
        group.setItems(venueItemList);

        List<Group<VenueItem>> groupList = new ArrayList<>();
        groupList.add(group);

        GroupResponse<VenueItem> groupResponse = new GroupResponse<>();
        groupResponse.setGroups(groupList);

        ResponseWrapper<VenueItem> wrapper = new ResponseWrapper<>();
        wrapper.setGroupResponse(groupResponse);
        return wrapper;
    }

    public static ResponseWrapper<VenueItem> wrapVenueList(List<Venue> venueList) {
        List<VenueItem> venueItemList = wrapVenueListAsItems(venueList);
        return wrapVenueItemList(venueItemList);
    }

    private static List<VenueItem> wrapVenueListAsItems(List<Venue> venueList) {
        List<VenueItem> venueItemList = new ArrayList<>();
        for (Venue venue : venueList) {
            VenueItem venueItem = new VenueItem();
            venueItem.setVenue(venue);
            venueItemList.add(venueItem);
        }

        return venueItemList;
    }
}
