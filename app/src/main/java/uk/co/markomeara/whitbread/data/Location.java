package uk.co.markomeara.whitbread.data;

import java.util.List;

public class Location {

    private List<String> formattedAddress;

    public List<String> getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(List<String> formattedAddress) {
        this.formattedAddress = formattedAddress;
    }
}
