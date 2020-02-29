import com.dropbox.core.*;

import java.io.IOException;

public class DropboxClient {

    public static void main(String[] args) throws DbxException, IOException {
        DropboxClientService dropboxClientService = new DropboxClientService();
        //dropboxClientService.getAccessToken(new String[]{"", "R4Lp_bVT52AAAAAAAAAAo3qbeFwaezOuOV3Xgg5K1-aTC8MJnUdnUhud9O72odo9", "3bkk0iniclu9a0x", "gwhz0y5n1fislqg"});
        if (args.length > 0) {
            if (args[0].equals("auth"))
                dropboxClientService.getAccessToken(args);
            if (args[0].equals("info"))
                dropboxClientService.getCurrentAccount(args);
            if (args[0].equals("list"))
                if (args[2].contains("."))
                    dropboxClientService.getListFolder(args);
                else
                    dropboxClientService.getMetadata(args);
        }

    }
}
