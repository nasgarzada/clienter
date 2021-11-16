package com.nicat.asgarzada.http;

import java.util.List;
import java.util.Map;

public class Response {
    private final int status;
    private final Map<String, List<String>> headers;
    private final String responseBody;

    public Response(int status, Map<String, List<String>> headers, String responseBody) {
        this.status = status;
        this.headers = headers;
        this.responseBody = responseBody;
    }

    public int getStatus() {
        return status;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public String getResponseBody() {
        return responseBody;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", headers=" + headers +
                ", responseBody='" + responseBody + '\'' +
                '}';
    }
}
