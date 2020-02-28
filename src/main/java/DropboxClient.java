import com.dropbox.core.*;

import java.io.IOException;

public class DropboxClient {

    public static void main(String[] args) throws DbxException, IOException {
        Service service = new Service();
        
        if(args.length > 0){
            if(args[0].equals("auth"))
                service.getAccessToken(args);
            if(args[0].equals("info"))
                service.getCurrentAccount(args);
            if(args[0].equals("list"))
                if(args[2].contains("."))
                    service.getListFolder(args);
                else
                    service.getMetadata(args);
        }
        
    }
}
