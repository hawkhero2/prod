package j.software.versions;

// import j.software.versions.ApiHandler;
// import java.io.BufferedReader;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.net.HttpURLConnection;
// import java.net.URI;
// import java.net.URL;
import java.util.ArrayList;


/**
 * Hello world!
 *
 */
public class App 
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
