package model;

public class DropboxFolderMetadata extends DropboxMetadata {
    public DropboxFolderMetadata() {
    }

    public DropboxFolderMetadata(String name, String pathLower, String pathDisplay, String id) {
        super(name, pathLower, pathDisplay, id);
    }

    @Override
    public String toString() {
        return getPathDisplay().substring(getPathLower().lastIndexOf('/') + 1) + " : dir";
    }
}
