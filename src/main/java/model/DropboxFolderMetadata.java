package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DropboxFolderMetadata extends DropboxMetadata {
    public DropboxFolderMetadata() {
    }

    public DropboxFolderMetadata(String tag, String name, String pathLower, String pathDisplay, String id) {
        super(tag, name, pathLower, pathDisplay, id);
    }
}
