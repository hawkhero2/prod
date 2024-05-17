package j.software.versions;

import java.util.ArrayList;

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
        String[] urls = {"https://versionhistory.googleapis.com/v1/chrome/platforms/win64/channels/canary/versions/all/releases?filter=endtime=none",
        "https://product-details.mozilla.org/1.0/firefox_versions.json"};

        for (String url : urls) {
            try {
                ApiHandler apiHandler = new ApiHandler();
                String responseStr = apiHandler.get(url);

                ArrayList<String> versions = new ArrayList<>(urls.length);

                for ( String item : responseStr.split(",")) {
                    
                    if ( url.contains("chrome")) {

                        if( item.contains("\"version\"") ) {
                            versions.add("chrome:"+item.split(":")[1]+",");
                        }
                    }

                    if ( url.contains("mozilla")) {
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
    }
}
