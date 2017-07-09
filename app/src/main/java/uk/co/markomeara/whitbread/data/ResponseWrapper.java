package uk.co.markomeara.whitbread.data;

import com.google.gson.annotations.SerializedName;

public class ResponseWrapper<T> {

    @SerializedName("response")
    private GroupResponse<T> groupResponse;

    public GroupResponse<T> getGroupResponse() {
        return groupResponse;
    }

    public void setGroupResponse(GroupResponse groupResponse) {
        this.groupResponse = groupResponse;
    }
}
