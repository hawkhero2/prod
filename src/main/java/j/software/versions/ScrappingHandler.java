package j.software.versions;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ScrappingHandler {
    
    public ScrappingHandler() {
        
    }

    public String get(String url) {
        String responseString="";

        try {
            // URL urlCon = new URI(url).toURL();
            Document doc = Jsoup.connect(url).timeout(10000).get();
            Element versionElement = doc.selectFirst("span.std.std-ref");
            responseString = versionElement.text();

        } catch (Exception e ) {
            e.printStackTrace();
        }

        return responseString;
    }
}
