import com.dropbox.core.*;

import java.io.IOException;

public class DropboxClient {

    public static void main(String[] args) throws DbxException, IOException {
        Service service = new Service();
        service.getMetadata(args);
        
        if(args.length > 0){
            //Service service = new Service();
            
            if(args[0].equals("auth"))
                service.getAccessToken(args);
            if(args[0].equals("info"))
                service.getCurrentAccount(args);
            if(args[0].equals("list"))
                service.getListFolder(args);
        }
        
    }
}
