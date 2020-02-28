import com.dropbox.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.DropboxAccount;
import model.DropboxFolderEntries;
import model.DropboxMetadata;
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

public class Service {
    private static final String ACCESS_TOKEN = "Bearer R4Lp_bVT52AAAAAAAAAAmdwkwfoPXgP-5L0hlFKx-Ai6CEmOqxW9S7st4GOUGfpH";
    private static final String APP_KEY = "3bkk0iniclu9a0x";
    private static final String APP_SECRET = "gwhz0y5n1fislqg";

    public void getAccessToken(String[] parameters) throws IOException, DbxException {

        try {
            // Create Dropbox client
            DbxRequestConfig config = new DbxRequestConfig(
                    "JavaTutorial/1.0", Locale.getDefault().toString());

            DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);//DbxAppInfo appInfo = new DbxAppInfo(parameters[1], parameters[2]);
            DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);

            String authorizeUrl = webAuth.start();
            System.out.println("1. Go to: " + authorizeUrl);
            System.out.println("2. Click \"Allow\" (you might have to log in first)");
            System.out.println("3. Copy the authorization code.");
            String code = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();

            DbxAuthFinish authFinish = webAuth.finish(code);
            String accessToken = authFinish.getAccessToken();
            System.out.println("Your access token:" + accessToken);
        }catch (Exception ex) {
            System.out.println("An Error has accorded, please try again with correct parameters!");
        }finally {
            System.out.println();
        }
    }

    public void getCurrentAccount(String[] parameters){
        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead

        try {

            HttpPost request = new HttpPost("https://api.dropboxapi.com/2/users/get_current_account");
            request.addHeader("Authorization", ACCESS_TOKEN);//request.addHeader("Authorization", parameters[1]);
            request.addHeader("Content-Type", "application/json");

            StringEntity params = new StringEntity("null");
            request.setEntity(params);

            HttpResponse response = httpClient.execute(request);
            System.out.println("--------------------------------------------------------");
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String jsonString = EntityUtils.toString(response.getEntity());

                ObjectMapper mapper = new ObjectMapper();
                mapper.enableDefaultTyping();
                DropboxAccount dropboxAccount =  mapper.readValue(jsonString, DropboxAccount.class);

                System.out.println(dropboxAccount);
            }
            else
                System.out.println("Error\n" + response.getEntity());

        }catch (Exception ex) {
            System.out.println("An Error has accorded, please try again with correct parameters!");
        } finally {
            System.out.println("--------------------------------------------------------");
        }
    }

    public void getListFolder(String[] parameters){
        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead

        try {

            HttpPost request = new HttpPost("https://api.dropboxapi.com/2/files/list_folder");
            request.addHeader("Authorization", ACCESS_TOKEN);//request.addHeader("Authorization", parameters[1]);
            request.addHeader("Content-Type", "application/json");

            JSONObject json = new JSONObject();
            json.put("path", "/Docs");//json.put("path", parameters[2]);
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
                mapper.enableDefaultTyping();
                DropboxFolderEntries folderEntries = mapper.readValue(jsonString, DropboxFolderEntries.class);

                System.out.println(folderEntries);
            }
            else
                System.out.println("Error\n" + response.getEntity());

        }catch (Exception ex) {
            System.out.println("An Error has accorded, please try again with correct parameters!");
        } finally {
            System.out.println("--------------------------------------------------------");
        }
    }

    public void getMetadata(String[] parameters){
        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead

        try {

            HttpPost request = new HttpPost("https://api.dropboxapi.com/2/files/get_metadata");
            request.addHeader("Authorization", ACCESS_TOKEN);//request.addHeader("Authorization", parameters[1]);
            request.addHeader("Content-Type", "application/json");

            JSONObject json = new JSONObject();
            json.put("path", "/Docs/Document.docx");//json.put("path", parameters[2]);
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
                DropboxMetadata dropboxMetadata = mapper.readValue(jsonString, DropboxMetadata.class);

                System.out.println(dropboxMetadata);
            }
            else
                System.out.println("Error\n" + response.getEntity());

        }catch (Exception ex) {
            System.out.println("An Error has accorded, please try again with correct parameters!");
        } finally {
            System.out.println("--------------------------------------------------------");
        }
    }
}
