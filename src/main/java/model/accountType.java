package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class accountType {
    @JsonProperty(".tag")
    private String tag;

    public accountType() {
    }

    public accountType(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
