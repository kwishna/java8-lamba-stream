package org.lambda.utils;

import java.net.HttpURLConnection;
import java.net.URL;

public class LinkUtils {
    public static int getResponseCode(String link) {
        URL url;
        HttpURLConnection httpURLConnection = null;
        int responseCode = 0;

        try {
            url = new URL(link);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            responseCode = httpURLConnection.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return responseCode;
    }
}
