import com.dropbox.core.*;
import com.dropbox.core.v1.DbxClientV1;
import com.dropbox.core.v1.DbxEntry;
import com.dropbox.core.v1.DbxWriteMode;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;

import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.Locale;

public class DropboxClient {
    private static final String ACCESS_TOKEN = "R4Lp_bVT52AAAAAAAAAAfvfSgBWmA2EjIM83OChH19jcmknApCod7bRaBj6kQYEE";
    private static final String APP_KEY = "3bkk0iniclu9a0x";
    private static final String APP_SECRET = "gwhz0y5n1fislqg";

    public static void main(String args[]) throws DbxException, IOException {
        // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig(
                "JavaTutorial/1.0", Locale.getDefault().toString());



        DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

        DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);

        //2 versions of clients
        DbxClientV1 clientV1 = new DbxClientV1(config, ACCESS_TOKEN);
        DbxClientV2 clientV2 = new DbxClientV2(config, ACCESS_TOKEN);

        // Get current account info
        FullAccount account = clientV2.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());

        // Get files and folder metadata from Dropbox root directory
        ListFolderResult result = clientV2.files().listFolder("");
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }

            result = clientV2.files().listFolderContinue(result.getCursor());
        }

        // Upload "test.txt" to Dropbox
        try (InputStream in = new FileInputStream("src/main/java/resources/test.txt")) {
            FileMetadata metadata = clientV2.files().uploadBuilder("/test.txt")
                    .uploadAndFinish(in);
        }

        //---------------------------------------
        // example code
        // Have the user sign in and authorize your app.
        String authorizeUrl = webAuth.start();
        System.out.println("1. Go to: " + authorizeUrl);
        System.out.println("2. Click \"Allow\" (you might have to log in first)");
        System.out.println("3. Copy the authorization code.");
        String code = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();

        DbxAuthFinish authFinish = webAuth.finish(code);
        String accessToken = authFinish.getAccessToken();

    }
}
