package com.nicat.asgarzada.clienter.http;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class ClientUtil {
    public static InputStream getResponseStream(HttpURLConnection httpConnection) throws IOException {
        return new BufferedInputStream(
                isSuccess(httpConnection.getResponseCode()) ?
                        httpConnection.getInputStream() :
                        httpConnection.getErrorStream()
        );
    }

    public static String convertToString(InputStream is) {
        try {
            var bytes = is.readAllBytes();
            return new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("can't be convert to string");
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isSuccess(int responseCode) {
        return responseCode >= 200 && responseCode < 300;
    }

}
