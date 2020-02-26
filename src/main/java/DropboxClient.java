import com.dropbox.core.*;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;
import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxAuthInfo;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuth;
import com.dropbox.core.json.JsonReader;

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

    public static void main(String[] args) throws DbxException, IOException {
        // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig(
                "JavaTutorial/1.0", Locale.getDefault().toString());

        DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

        DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);

        String authorizeUrl = webAuth.start();
        System.out.println("1. Go to: " + authorizeUrl);
        System.out.println("2. Click \"Allow\" (you might have to log in first)");
        System.out.println("3. Copy the authorization code.");

        String code = new BufferedReader(new InputStreamReader(System.in)).readLine();
        if (code == null) {
            System.exit(1); return;
        }
        code = code.trim();

        DbxAuthFinish authFinish;
        try {
            authFinish = webAuth.finish(code);
        } catch (DbxException ex) {
            System.err.println("Error in DbxWebAuth.authorize: " + ex.getMessage());
            System.exit(1); return;
        }
        String accessToken = authFinish.getAccessToken();
        System.out.println("\nYour access token: " + accessToken);

        //Specification: https://www.dropbox.com/developers/documentation/http/documentation#users-get_current_account
        //> java -jar dropbox-client.jar info {accessToken} {locale}
        System.out.println("--------------------------------------------------------\n" +
                "User ID: "+ authFinish.getUserId() +"\n" +
                "Display name: \"+ authFinish.getUserId() +\"\n" +
                "Name: "+ authFinish.getUserId() +" "+ authFinish.getUserId() +" ("+ authFinish.getUserId() +")\n" +
                "E-mail: "+ authFinish.getUserId() +" (verified)\n" +
                "Country: "+ authFinish.getUserId() +"\n" +
                "Referral link: "+ authFinish.getUserId() +"\n" +
                "--------------------------------------------------------");


        //Specification: https://www.dropbox.com/developers/documentation/http/documentation#files-get_metadata and https://www.dropbox.com/developers/documentation/http/documentation#files-list_folder
        //> java -jar dropbox-client.jar list {accessToken} {path} {locale}

        //Expected output for a folder:
        System.out.println("--------------------------------------------------------\n" +
                "/Docs : dir\n" +
                "- /Test : dir\n" +
                "- /iffiles1.rar : file, 309.5 KB, application/rar,\n" +
                "modified at: \"04-11-2012 11:50:19\"\n" +
                "- /public-003.zip : file, 1 MB, application/zip, modified\n" +
                "at: \"23-04-2013 22:12:40\"\n" +
                "- /Test.java : file, 99 bytes, text/x-java, modified\n" +
                "at: \"04-12-2012 11:47:59\"\n" +
                "--------------------------------------------------------");

        //Expected output for a file:
        System.out.println("\"/Docs/iffiles1.rar\"\n" +
                "--------------------------------------------------------\n" +
                "/Docs/iffiles1.rar : file, 309.5 KB, application/rar,\n" +
                "modified at: \"04-11-2012 11:50:19\"\n" +
                "--------------------------------------------------------");

        //-------------------------------------------------------------------
        //-------------------------------------------------------------------
        //Extra code
        System.out.println("Authorization complete.");
        System.out.println("- User ID: " + authFinish.getUserId());
        System.out.println("- Account ID: " + authFinish.getAccountId());
        System.out.println("- Access Token: " + authFinish.getAccessToken());

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

        // Save auth information to output file.
        DbxAuthInfo authInfo = new DbxAuthInfo(authFinish.getAccessToken(), appInfo.getHost());
        File output = new File("src/main/java/resources/test.txt");
        try {
            DbxAuthInfo.Writer.writeToFile(authInfo, output);
            System.out.println("Saved authorization information to \"" + output.getCanonicalPath() + "\".");
        } catch (IOException ex) {
            System.err.println("Error saving to <auth-file-out>: " + ex.getMessage());
            System.err.println("Dumping to stderr instead:");
            DbxAuthInfo.Writer.writeToStream(authInfo, System.err);
            System.exit(1); return;
        }
    }
}
