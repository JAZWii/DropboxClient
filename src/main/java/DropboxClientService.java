import com.dropbox.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.DropboxAccount;
import model.DropboxFileMetadata;
import model.DropboxFolderEntries;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class DropboxClientService {

    public void getAccessToken(String[] parameters) throws IOException, DbxException {

        try {
            // Create Dropbox client
            DbxRequestConfig config = new DbxRequestConfig(
                    "JavaTutorial/1.0", Locale.getDefault().toString());

            DbxAppInfo appInfo = new DbxAppInfo(parameters[1], parameters[2]);
            DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);

            String authorizeUrl = webAuth.start();
            System.out.println("1. Go to: " + authorizeUrl);
            System.out.println("2. Click \"Allow\" (you might have to log in first)");
            System.out.println("3. Copy the authorization code.");
            String code = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();

            DbxAuthFinish authFinish = webAuth.finish(code);
            String accessToken = authFinish.getAccessToken();
            System.out.println("Your access token:" + accessToken);
        } catch (Exception ex) {
            System.out.println("An Error has accorded, please try again with correct parameters!");
        } finally {
            System.out.println();
        }
    }

    public DropboxAccount getCurrentAccount(String[] parameters) {
        HttpClient httpClient = HttpClientBuilder.create().build();

        DropboxAccount dropboxAccount = new DropboxAccount();
        try {

            HttpPost request = new HttpPost("https://api.dropboxapi.com/2/users/get_current_account");
            request.addHeader("Authorization", "Bearer " + parameters[1]);
            request.addHeader("Content-Type", "application/json");

            StringEntity params = new StringEntity("null");
            request.setEntity(params);

            HttpResponse response = httpClient.execute(request);
            System.out.println("--------------------------------------------------------");
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String jsonString = EntityUtils.toString(response.getEntity());

                ObjectMapper mapper = new ObjectMapper();
                mapper.enableDefaultTyping();
                dropboxAccount = mapper.readValue(jsonString, DropboxAccount.class);

                System.out.println(dropboxAccount);
            } else
                System.out.println("Error\n" + response.getEntity());

        } catch (Exception ex) {
            System.out.println("An Error has accorded, please try again with correct parameters!");
        } finally {
            System.out.println("--------------------------------------------------------");
        }
        return dropboxAccount;
    }

    public DropboxFolderEntries getListFolder(String[] parameters) {
        HttpClient httpClient = HttpClientBuilder.create().build();

        DropboxFolderEntries folderEntries = new DropboxFolderEntries();
        try {

            HttpPost request = new HttpPost("https://api.dropboxapi.com/2/files/list_folder");
            request.addHeader("Authorization", "Bearer " + parameters[1]);
            request.addHeader("Content-Type", "application/json");

            JSONObject json = new JSONObject();
            json.put("path", parameters[2]);
            json.put("recursive", false);
            json.put("include_media_info", false);
            json.put("include_deleted", false);
            json.put("include_has_explicit_shared_members", false);
            json.put("include_mounted_folders", true);
            json.put("include_non_downloadable_files", true);

            StringEntity params = new StringEntity(json.toString());
            request.setEntity(params);

            HttpResponse response = httpClient.execute(request);
            System.out.println("--------------------------------------------------------");
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String jsonString = EntityUtils.toString(response.getEntity());

                ObjectMapper mapper = new ObjectMapper();
                mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT);
                folderEntries = mapper.readValue(jsonString, DropboxFolderEntries.class);

                System.out.println(parameters[2] + " : dir");
                System.out.print(folderEntries);
            } else
                System.out.println("Error\n" + response.getEntity());

        } catch (Exception ex) {
            System.out.println("An Error has accorded, please try again with correct parameters!");
        } finally {
            System.out.println("--------------------------------------------------------");
        }
        return folderEntries;
    }

    public DropboxFileMetadata getMetadata(String[] parameters) {
        HttpClient httpClient = HttpClientBuilder.create().build();

        DropboxFileMetadata dropboxFileMetadata = new DropboxFileMetadata();
        try {

            HttpPost request = new HttpPost("https://api.dropboxapi.com/2/files/get_metadata");
            request.addHeader("Authorization", "Bearer " + parameters[1]);
            request.addHeader("Content-Type", "application/json");

            JSONObject json = new JSONObject();
            json.put("path", parameters[2]);
            json.put("include_media_info", false);
            json.put("include_deleted", false);
            json.put("include_has_explicit_shared_members", false);

            StringEntity params = new StringEntity(json.toString());
            request.setEntity(params);

            HttpResponse response = httpClient.execute(request);
            System.out.println("--------------------------------------------------------");
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String jsonString = EntityUtils.toString(response.getEntity());

                ObjectMapper mapper = new ObjectMapper();
                mapper.enableDefaultTyping();
                dropboxFileMetadata = mapper.readValue(jsonString, DropboxFileMetadata.class);

                System.out.println(dropboxFileMetadata);
            } else
                System.out.println("Error\n" + response.getEntity());

        } catch (Exception ex) {
            System.out.println("An Error has accorded, please try again with correct parameters!");
        } finally {
            System.out.println("--------------------------------------------------------");
        }
        return dropboxFileMetadata;
    }
}
