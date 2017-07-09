package uk.co.markomeara.whitbread.data;

import java.util.List;

public class GroupResponse<T> {

    private List<Group<T>> groups;

    public List<Group<T>> getGroups() {
        return groups;
    }

    public void setGroups(List<Group<T>> groups) {
        this.groups = groups;
    }
}
