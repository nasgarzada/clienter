package com.nicat.asgarzada.example;

import com.nicat.asgarzada.http.HttpClient;
import com.nicat.asgarzada.http.Method;
import com.nicat.asgarzada.http.SslHelper;

public class ApiCallExample {
    public static void main(String[] args) {
        SslHelper.ignoreSSl();
        var response = HttpClient.of("https://cat-fact.herokuapp.com/facts/")
                .method(Method.GET)
                .call();

        System.out.println(response);
    }
}
