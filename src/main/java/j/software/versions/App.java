package j.software.versions;

import j.software.versions.ApiHandler;
import j.software.versions.ScrappingHandler;


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
        String[] urls = {"https://www.adobe.com/devnet-docs/acrobatetk/tools/ReleaseNotesDC/index.html","https://helpx.adobe.com/acrobat/release-note/release-notes-acrobat-reader.html",
        "https://www.cisco.com/c/en/us/support/security/anyconnect-secure-mobility-client/products-release-notes-list.html",
        "https://www.irfanview.com/main_history.htm",
        "https://www.videolan.org/vlc/releases/"};

        String[] apiUrls = {"https://versionhistory.googleapis.com/v1/chrome/platforms/win64/channels/canary/versions/all/releases?filter=endtime=none",
        "https://product-details.mozilla.org/1.0/firefox_versions.json"};

        // for (String url : apiUrls) {
        //     try {
        //         ApiHandler apiHandler = new ApiHandler();
        //         String responseStr = apiHandler.get(url);

        //         ArrayList<String> versions = new ArrayList<>(apiUrls.length);

        //         for ( String item : responseStr.split(",")) {
                    
        //             if (url.contains("chrome")) {

        //                 if(item.contains("\"version\"") ) {
        //                     versions.add("chrome:"+item.split(":")[1]+",");
        //                 }
        //             }

        //             if (url.contains("mozilla")) {
        //                 if (item.contains("\"LATEST_FIREFOX_VERSION\"")) {
        //                     versions.add(item+",");
        //                 }
        //             }
        //         }
        //     System.out.println(versions.toString());
        //     } catch (Exception e) {
        //         e.printStackTrace();
        //     }
        // }

        // for (String url : urls) {
        //     try {
        //         ScrappingHandler scrappingHandler = new ScrappingHandler();
        //         System.out.println(scrappingHandler.get(url));
                
        //     } catch (Exception e) {
        //         e.printStackTrace();
        //     }
        // }

        ScrappingHandler scrappingHandler = new ScrappingHandler();
        System.out.println(scrappingHandler.get(urls[0]));
    }
}
