package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = ".tag")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DropboxFolderMetadata.class, name = "folder"),
        @JsonSubTypes.Type(value = DropboxFileMetadata.class, name = "file")
})
public abstract class DropboxMetadata {
    @JsonProperty(".tag")
    private String tag;

    @JsonProperty("name")
    private String name;

    @JsonProperty("path_lower")
    private String pathLower;

    @JsonProperty("path_display")
    private String pathDisplay;

    @JsonProperty("id")
    private String id;

    public DropboxMetadata() {
    }

    public DropboxMetadata(String tag, String name, String pathLower, String pathDisplay, String id) {
        this.tag = tag;
        this.name = name;
        this.pathLower = pathLower;
        this.pathDisplay = pathDisplay;
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathLower() {
        return pathLower;
    }

    public void setPathLower(String pathLower) {
        this.pathLower = pathLower;
    }

    public String getPathDisplay() {
        return pathDisplay;
    }

    public void setPathDisplay(String pathDisplay) {
        this.pathDisplay = pathDisplay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
