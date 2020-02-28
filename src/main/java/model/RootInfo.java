package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RootInfo {
    @JsonProperty(".tag")
    private String tag;

    @JsonProperty("root_namespace_id")
    private String rootNamespaceId;

    @JsonProperty("home_namespace_id")
    private String homeNamespaceId;

    public RootInfo() {
    }

    public RootInfo(String tag, String rootNamespaceId, String homeNamespaceId) {
        this.tag = tag;
        this.rootNamespaceId = rootNamespaceId;
        this.homeNamespaceId = homeNamespaceId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getRootNamespaceId() {
        return rootNamespaceId;
    }

    public void setRootNamespaceId(String rootNamespaceId) {
        this.rootNamespaceId = rootNamespaceId;
    }

    public String getHomeNamespaceId() {
        return homeNamespaceId;
    }

    public void setHomeNamespaceId(String homeNamespaceId) {
        this.homeNamespaceId = homeNamespaceId;
    }
}
