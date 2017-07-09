package uk.co.markomeara.whitbread.data;

public class Category {

    private String id;
    private String name;
    private String shortname;
    private VenueIcon icon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public VenueIcon getIcon() {
        return icon;
    }

    public void setIcon(VenueIcon icon) {
        this.icon = icon;
    }
}
