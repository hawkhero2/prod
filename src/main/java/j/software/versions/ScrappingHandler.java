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
    String url;
    
    public ScrappingHandler() {
    }

    public ScrappingHandler(String url) {
       this.url = url; 
    }

    public String irfanviewParser(Document doc) {
        String responseString="";
        
        Element versionElement = doc.selectFirst("h3");
        if (versionElement != null ) {
            return responseString = "IrfanView: "+versionElement.text();
        }
        
       return responseString;
    }

    public String skypeParser( Document doc) {
        String responseString="";
        Elements elements = doc.select("p");
        for (Element item : elements) {
            if(item.text().contains("Skype for Windows Desktop version")){
            return responseString = "Skype: "+item.text();
            }
        }
        return responseString;
    }

    public String vlcParser(Document doc) {
        String responseString="";

        // VLC
        Elements elements = doc.select("a");
        for (Element item : elements) {
            if ((item.text() != null) && (item.text().contains("VLC")) && (item.attr("href").contains("/vlc/releases/")) ) {
                return responseString = "VLC: "+item.text();
            } 
        }
        return responseString;
    }

    public String adobeParser(Document doc) {
        String responseString="";

        Element versionElement = doc.selectFirst("span[class='std std-ref']");
        return responseString = "Adobe Acrobat: "+versionElement.text();
    }

    public String ciscoParser(Document doc) {
        String responseString="";

        Elements elements = doc.select("a[data-id='link4']");
        if (elements.size() > 0) {
            for (Element item : elements) {
                if (item.text().contains("AnyConnect Secure Mobility Client")) {
                    String splitted = item.text().split(",")[1];
                    return responseString = "Cisco: "+splitted;
                }
            }
        }

        return responseString;
    }

    public String fortiParser(Document doc) {
        String responseString="";
        Element element = doc.selectFirst("h2[class='new--design']");
        if ((element != null) && (element.text().contains("FortiClient"))) {
            return responseString = "FortiClient: "+element.text().trim().split(" ")[1];
        }

        return responseString;
    }

    public String libreOfficeParser(Document doc) {
        String responseString="";
        Elements elements = doc.select("h4");
        for(Element item : elements) {
            if( (item.text() != null) && (item.text().contains("Latest Release")) ) {
                return responseString = "LibreOffice: "+item.text();
            }
        }
        return responseString;
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
            
            if (url.contains("forti")) {
                responseString = fortiParser(doc);
            }
            
        } catch (Exception e ) {
            e.printStackTrace();
        }

        return responseString;
    }
}
