import com.dropbox.core.*;

import java.io.IOException;

public class DropboxClient {

    public static void main(String[] args) throws DbxException, IOException {
        DropboxClientService dropboxClientService = new DropboxClientService();

        if (args.length > 0) {
            if (args[0].equals("auth"))
                dropboxClientService.getAccessToken(args);
            if (args[0].equals("info"))
                dropboxClientService.getCurrentAccount(args);
            if (args[0].equals("list") && args.length > 2)
                if (args[2].contains("."))
                    dropboxClientService.getListFolder(args);
                else
                    dropboxClientService.getMetadata(args);
        }
    }
}
