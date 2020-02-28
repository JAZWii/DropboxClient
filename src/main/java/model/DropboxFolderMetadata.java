package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DropboxFolderMetadata extends DropboxMetadata {
    public DropboxFolderMetadata() {
    }

    public DropboxFolderMetadata(String name, String pathLower, String pathDisplay, String id) {
        super(name, pathLower, pathDisplay, id);
    }

    @Override
    public String toString() {
        return getPathLower().substring(getPathLower().lastIndexOf('/')) +" : dir";
    }
}
