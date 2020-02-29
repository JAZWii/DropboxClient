import model.DropboxAccount;
import model.DropboxFileMetadata;
import model.DropboxFolderEntries;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.*;

public class DropboxClientTest {
    DropboxClientService dropboxClientService = new DropboxClientService();
    private final static String ACCESS_TOKEN = "R4Lp_bVT52AAAAAAAAAApeELXnnLFFTqmYdL9s98grfHIj6KMB21QoWRCt5vWPXp";

    @Test
    void getCurrentAccount() {
        DropboxAccount dropboxAccount = dropboxClientService.getCurrentAccount(new String[]{"info", ACCESS_TOKEN});
        assertEquals("Jawad", dropboxAccount.getName().getGivenName());
        assertEquals("Almarhoon", dropboxAccount.getName().getSurname());
        assertEquals("Jawad", dropboxAccount.getName().getFamiliarName());
        assertEquals("Jawad Almarhoon", dropboxAccount.getName().getDisplayName());
        assertEquals("JA", dropboxAccount.getName().getAbbreviatedName());

        assertEquals("jazwii2216@gmail.com", dropboxAccount.getEmail());
        assertTrue(dropboxAccount.isEmailVerified());
        assertEquals("https://dl-web.dropbox.com/account_photo/get/dbaphid%3AAAD0XysKDHN0Q0Nwp0ylT_f-TB1Q59Wz2jE?size=128x128&vers=1562209710038", dropboxAccount.getProfilePhotoUrl());
        assertFalse(dropboxAccount.isDisabled());
        assertEquals("ES", dropboxAccount.getCountry());
        assertEquals("en", dropboxAccount.getLocale());
        assertEquals("https://www.dropbox.com/referrals/AABUPb5w9j8KVqL_10SzTWvlvILgAyE8VPk?src=app9-7144432", dropboxAccount.getReferralLink());
        assertFalse(dropboxAccount.isPaired());

    }

    @Test
    void getListFolder() {
        DropboxFolderEntries folderEntries = dropboxClientService.getListFolder(new String[]{"list", ACCESS_TOKEN, "/Docs"});

        assertEquals("Test", folderEntries.getEntries().get(0).getName());
        assertEquals("/docs/test", folderEntries.getEntries().get(0).getPathLower());
        assertEquals("/Docs/Test", folderEntries.getEntries().get(0).getPathDisplay());
        assertEquals("id:yfFqgFNtZTAAAAAAAAAAIg", folderEntries.getEntries().get(0).getId());

        assertEquals("Document.docx", folderEntries.getEntries().get(1).getName());
        assertEquals("/docs/document.docx", folderEntries.getEntries().get(1).getPathLower());
        assertEquals("/Docs/Document.docx", folderEntries.getEntries().get(1).getPathDisplay());
        assertEquals("id:yfFqgFNtZTAAAAAAAAAAIw", folderEntries.getEntries().get(1).getId());
        assertEquals("0159f761fba645d00000001aac1d9d0", ((DropboxFileMetadata)folderEntries.getEntries().get(1)).getRev());
        assertEquals(11018, ((DropboxFileMetadata)folderEntries.getEntries().get(1)).getSize());
        assertTrue(((DropboxFileMetadata)folderEntries.getEntries().get(1)).getIsDownloadable());
        assertEquals("c9f714f99af3870cffe9eaf17b1793c8180f3e9950089cf1eb7e0071a9427457", ((DropboxFileMetadata)folderEntries.getEntries().get(1)).getContentHash());

        assertEquals("HelloWorld.java", folderEntries.getEntries().get(2).getName());
        assertEquals("/docs/helloworld.java", folderEntries.getEntries().get(2).getPathLower());
        assertEquals("/Docs/HelloWorld.java", folderEntries.getEntries().get(2).getPathDisplay());
        assertEquals("id:yfFqgFNtZTAAAAAAAAAAJQ", folderEntries.getEntries().get(2).getId());
        assertEquals("0159fb2bbd4aac300000001aac1d9d0", ((DropboxFileMetadata)folderEntries.getEntries().get(2)).getRev());
        assertEquals(290, ((DropboxFileMetadata)folderEntries.getEntries().get(2)).getSize());
        assertTrue(((DropboxFileMetadata)folderEntries.getEntries().get(2)).getIsDownloadable());
        assertEquals("c5bf042d4d9b5d20dbd9eba3d9f66e28f7cd175def61c8d2ef0419c6967e086c", ((DropboxFileMetadata)folderEntries.getEntries().get(2)).getContentHash());

        assertFalse(folderEntries.isHasMore());
    }

    @Test
    void getMetadata() {
        DropboxFileMetadata dropboxFileMetadata = dropboxClientService.getMetadata(new String[]{"list", ACCESS_TOKEN, "/Docs/HelloWorld.java"});

        assertEquals("HelloWorld.java", dropboxFileMetadata.getName());
        assertEquals("/docs/helloworld.java", dropboxFileMetadata.getPathLower());
        assertEquals("/Docs/HelloWorld.java", dropboxFileMetadata.getPathDisplay());
        assertEquals("id:yfFqgFNtZTAAAAAAAAAAJQ", dropboxFileMetadata.getId());
        assertEquals("0159fb2bbd4aac300000001aac1d9d0", dropboxFileMetadata.getRev());
        assertEquals(290, dropboxFileMetadata.getSize());
        assertTrue(dropboxFileMetadata.getIsDownloadable());
        assertEquals("c5bf042d4d9b5d20dbd9eba3d9f66e28f7cd175def61c8d2ef0419c6967e086c", dropboxFileMetadata.getContentHash());
    }
}
