package j.software.versions;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

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
            
            // Adobe Acrobat
            if (url.contains("adobe")) {
                Element versionElement = doc.selectFirst("span[class='std std-ref']");
                return responseString = "Adobe Acrobat: "+versionElement.text();
            }

            // Cisco
            if (url.contains("cisco")) {
               Elements elements = doc.select("a[data-id='link4']");
                if ( elements.size() > 0) {
                    for ( Element item : elements) {
                         if ( item.text().contains("AnyConnect Secure Mobility Client")) {
                            return responseString = "Cisco: "+item.text();
                            //  break;
                         }
                     }
                }
                else{
                    System.out.println("No Cisco version found");
                }
            }

            // irfanview
            if (url.contains("irfanview")) {
                Element versionElement = doc.selectFirst("h3");
                if ( versionElement != null ) {
                    return responseString = "IrfanView: "+versionElement.text();
                }
            }

            // VLC
            if ( url.contains("videolan")) {
                Elements elements = doc.select("a");
                for ( Element item : elements) {
                    if ( (item.text() != null) && (item.text().contains("VLC")) && (item.attr("href").contains("/vlc/releases/")) ) {
                        System.out.println("VLC version found: "+item.text());
                        return responseString = "VLC: "+item.text();
                        // break;
                    } else {
                        System.out.println("No VLC version found");
                    }
                }
            }

            // Skype
            if ( url.contains("skype")) {
                Elements elements = doc.select("p");

                for (Element item : elements) {

                    if( item.text().contains("Skype for Windows Desktop version")){
                    return responseString = "Skype: "+item.text();
                    }
                }
            } 
        } catch (Exception e ) {
            e.printStackTrace();
        }

        return responseString;
    }
}
