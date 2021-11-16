package com.nicat.asgarzada.clienter.example;

import com.nicat.asgarzada.clienter.http.HttpClient;
import com.nicat.asgarzada.clienter.http.Method;
import com.nicat.asgarzada.clienter.http.SslHelper;

public class ApiCallExample {
    public static void main(String[] args) {
        SslHelper.ignoreSSl();
        var response = HttpClient.of("https://cat-fact.herokuapp.com/facts/")
                .method(Method.GET)
                .call();

        System.out.println(response);
    }
}
