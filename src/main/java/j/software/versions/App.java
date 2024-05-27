package j.software.versions;

import j.software.versions.ApiHandler;
import j.software.versions.ScrappingHandler;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;


public class App 

/* TODO
Add the API urls for the following apps: 
Chrome, Firefox, Adobe Acrobat Reader DC,Cisco AnyConnect,
Citrix Workspace,IrfanView,Java 8,Skype™,VLC media player,
Thunderbird, Greenshot, OpenOffice, Microsoft Office Home and Business 2013 Microsoft Office Home and Business 2016 ,
Windows Defender Security Center,Sonicwall NetExtender,PGina
FortiClient, Azure VPN Client, Libre Office
*/
 
 
{
    public static void main( String[] args ) {
        String[] urls = {"https://www.adobe.com/devnet-docs/acrobatetk/tools/ReleaseNotesDC/index.html",
        "https://www.cisco.com/c/en/us/support/security/anyconnect-secure-mobility-client/products-release-notes-list.html",
        "https://www.irfanview.com/main_history.htm",
        "https://www.videolan.org/vlc/releases/",
        "https://support.microsoft.com/en-us/skype/what-is-the-latest-version-of-skype-on-each-platform-7423a24f-1f26-4782-85d6-27abdaadd5ad"};

        String[] apiUrls = {"https://versionhistory.googleapis.com/v1/chrome/platforms/win64/channels/canary/versions/all/releases?filter=endtime=none",
        "https://product-details.mozilla.org/1.0/firefox_versions.json"};

        ArrayList<String> versions = new ArrayList<String>(urls.length+apiUrls.length);
        for (String url : apiUrls) {
            try {
                ApiHandler apiHandler = new ApiHandler();
                String responseStr = apiHandler.get(url);

                for ( String item : responseStr.split(",")) {
                    
                    if (url.contains("chrome")) {

                        if(item.contains("\"version\"") ) {
                            versions.add("chrome:"+item.split(":")[1]+",");
                        }
                    }

                    if (url.contains("mozilla")) {
                        if (item.contains("\"LATEST_FIREFOX_VERSION\"")) {
                            versions.add(item+",");
                        }
                    }
                }
            System.out.println(versions.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (String url : urls) {
            try {
                ScrappingHandler scrappingHandler = new ScrappingHandler();
                versions.add(scrappingHandler.get(url));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        try {
            FileWriter writer = new FileWriter("versions.txt", true);

            for ( String version : versions) {
                writer.write(version);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
