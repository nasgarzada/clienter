package com.nicat.asgarzada.clienter.http;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HttpClient {
    private final HttpURLConnection httpURLConnection;
    private static final List<Method> bodyAllowedMethods = List.of(Method.POST, Method.PUT);

    private HttpClient(String url) throws IOException {
        this.httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
    }

    public static HttpClient of(String url) {
        try {
            return new HttpClient(url);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("can't start http client");
        }
    }

    public HttpClient method(Method method) {
        try {
            this.httpURLConnection.setRequestMethod(method.name());
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        return this;
    }

    public HttpClient headers(Map<String, String> header) {
        Objects.requireNonNull(header);
        header.forEach(this.httpURLConnection::addRequestProperty);
        return this;
    }

    public HttpClient body(String body) {
        if (isBodyAllowed()) {
            try {
                this.httpURLConnection.setDoOutput(true);
                var os = this.httpURLConnection.getOutputStream();
                var osw = new OutputStreamWriter(os);
                osw.write(body);
                osw.flush();
                osw.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public Response call() {
        try {
            this.httpURLConnection.connect();
            var body = ClientUtil.convertToString(
                    ClientUtil.getResponseStream(this.httpURLConnection)
            );
            var headers = this.httpURLConnection.getHeaderFields();
            this.httpURLConnection.disconnect();

            return new Response(
                    this.httpURLConnection.getResponseCode(),
                    headers,
                    body
            );
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private boolean isBodyAllowed() {
        var httpMethod = Method.valueOf(this.httpURLConnection.getRequestMethod());
        return HttpClient.bodyAllowedMethods.contains(httpMethod);
    }


}
