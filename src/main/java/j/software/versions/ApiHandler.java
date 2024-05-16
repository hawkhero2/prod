package j.software.versions;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class ApiHandler {
    
    public ApiHandler() {

    }

    /**
     * @param url
     * @return
     */
    public String get(String url) {
        String response = "";

        try {

            URL urlCon = new URI(url).toURL();
            HttpURLConnection connection = (HttpURLConnection) urlCon.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
        
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder strBuilder = new StringBuilder();
            String lineString;

             while ((lineString = reader.readLine()) != null) {
                    strBuilder.append(lineString);
                }
                reader.close();
            response=strBuilder.toString();

        } catch (Exception e ) {
            e.printStackTrace();
        }

        return response;
    }

}
