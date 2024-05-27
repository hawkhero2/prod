package j.software.versions;


import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScrappingHandler {
    
    public ScrappingHandler() {
        
    }

    public String get(String url) {
        String responseString="";

        try {
            URL urlCon = new URI(url).toURL();
            HttpURLConnection connection = (HttpURLConnection) urlCon.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputStr;
            StringBuilder strBuilder = new StringBuilder();

            while ((inputStr=bufReader.readLine())!=null) {
                strBuilder.append(inputStr);
            }
            bufReader.close();
            String htmlStr = strBuilder.toString();

            Document doc = Jsoup.parse(htmlStr);
            if (url.contains("adobe")) {
                Element versionElement = doc.selectFirst("span[class='std std-ref']");
                responseString = versionElement.text();
            }

            if (url.contains("cisco")) {
               Elements elements = doc.select("a[data-id='link4']");
                if ( elements.size() > 0) {
                    for ( Element item : elements) {
                         if ( item.text().contains("AnyConnect Secure Mobility Client")) {
                             responseString = item.text();
                             break;
                         }
                     }
                }
                else{
                    System.out.println("No Cisco version found");
                }
            }
        } catch (Exception e ) {
            e.printStackTrace();
        }

        return responseString;
    }
}
