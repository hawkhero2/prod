package j.software.versions;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        String[] urls = {"https://versionhistory.googleapis.com/v1/chrome/platforms/win64/channels/canary/versions/all/releases?filter=endtime=none"};

        for (String url : urls) {
            try {
                URL urlCon = new URL(url);
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
                // System.out.println(strBuilder.toString());
                reader.close();
                JSONObject jsonObject = new JSONObject(strBuilder.toString());
                System.out.println(jsonObject.toString());
                String releases = jsonObject.get("releases").toString();
                // JSONObject releasesJson = new JSONObject(jsonObject.get(releases).toString());
                System.out.println(releases);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
