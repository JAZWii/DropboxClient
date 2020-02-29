package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.FileNameMap;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DropboxFileMetadata extends DropboxMetadata {

    @JsonProperty("client_modified")
    private String clientModified;

    @JsonProperty("server_modified")
    private String serverModified;

    @JsonProperty("rev")
    private String rev;

    @JsonProperty("size")
    private int size;

    @JsonProperty("is_downloadable")
    private boolean isDownloadable;

    @JsonProperty("content_hash")
    private String contentHash;

    public DropboxFileMetadata() {
    }

    public DropboxFileMetadata(String name, String pathLower, String pathDisplay, String id, String clientModified, String serverModified, String rev, int size, boolean isDownloadable, String contentHash) {
        super(name, pathLower, pathDisplay, id);
        this.clientModified = clientModified;
        this.serverModified = serverModified;
        this.rev = rev;
        this.size = size;
        this.isDownloadable = isDownloadable;
        this.contentHash = contentHash;
    }

    public String getClientModified() {
        return clientModified;
    }

    public void setClientModified(String clientModified) {
        this.clientModified = clientModified;
    }

    public String getServerModified() {
        return serverModified;
    }

    public void setServerModified(String serverModified) {
        this.serverModified = serverModified;
    }

    public String getRev() {
        return rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean getIsDownloadable() {
        return isDownloadable;
    }

    public void setIsDownloadable(boolean isDownloadable) {
        this.isDownloadable = isDownloadable;
    }

    public String getContentHash() {
        return contentHash;
    }

    public void setContentHash(String contentHash) {
        this.contentHash = contentHash;
    }

    @Override
    public String toString() {//todo file type
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = "";
        try {
            date = simpleDateFormat.format(format.parse(clientModified.replace('T', ' ').substring(0, clientModified.length() - 1)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String mimeType = fileNameMap.getContentTypeFor(getName());

        return "file, " + convertSize(getSize()) + ", " + (mimeType == null ? "Unknown/type" : mimeType) + ", modified at: \"" + date + "\"";
    }

    private String convertSize(int size) {
        String sizeType = " Byte";
        if (size >= 1024) {
            size /= 1024;
            sizeType = " KB";
        }
        if (size >= 1024) {
            size /= 1024;
            sizeType = " MG";
        }
        if (size >= 1024) {
            size /= 1024;
            sizeType = " GB";
        }
        if (size >= 1024) {
            size /= 1024;
            sizeType = " TB";
        }
        return size + sizeType;
    }
}
