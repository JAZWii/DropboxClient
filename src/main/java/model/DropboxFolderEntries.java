package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DropboxFolderEntries {
    @JsonProperty("entries")
    private List<DropboxMetadata> entries;

    @JsonProperty("cursor")
    private String cursor;

    @JsonProperty("has_more")
    private boolean hasMore;

    public DropboxFolderEntries() {
    }

    public DropboxFolderEntries(List<DropboxMetadata> entries, String cursor, boolean hasMore) {
        this.entries = entries;
        this.cursor = cursor;
        this.hasMore = hasMore;
    }

    public List<DropboxMetadata> getEntries() {
        return entries;
    }

    public void setEntries(List<DropboxMetadata> entries) {
        this.entries = entries;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
}
