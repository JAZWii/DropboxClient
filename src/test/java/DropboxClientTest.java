import model.DropboxFileMetadata;
import model.DropboxFolderEntries;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;

public class DropboxClientTest {
    DropboxClientService dropboxClientService = new DropboxClientService();
    private final static String ACCESS_TOKEN = "R4Lp_bVT52AAAAAAAAAAo3qbeFwaezOuOV3Xgg5K1-aTC8MJnUdnUhud9O72odo9";

    @Test
    void getCurrentAccount(String [] args) {

    }

    @Test
    void getListFolder(String [] args) {
        DropboxFolderEntries folderEntries = dropboxClientService.getListFolder(args);
        //assertEquals("hello world", dropboxFileMetadata.getId());
    }

    @Test
    void getMetadata(String [] args) {
        DropboxFileMetadata dropboxFileMetadata = dropboxClientService.getMetadata(new String[]{"list", ACCESS_TOKEN, "/Doc/HelloWorld.java"});
        assertEquals("hello world", dropboxFileMetadata.getId());

    }
}
