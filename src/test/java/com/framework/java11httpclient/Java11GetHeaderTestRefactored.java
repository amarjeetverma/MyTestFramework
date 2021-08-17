package com.framework.java11httpclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Java11GetHeaderTestRefactored {
    private static final String BASE_URL = "https://api.github.com/";

    @Test
    void getReturns200() throws IOException, InterruptedException {
        //Arrange
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URL))
                .setHeader("USER-AGENT", "JAVA 11 HTTP BOT")
                .build();
        //Act
        HttpResponse<Void> response = httpClient.send(get, HttpResponse.BodyHandlers.discarding());
        int actualCode = response.statusCode();
        //Assert
        Assertions.assertEquals(200, actualCode);
    }

    @ParameterizedTest
    @CsvSource({
            "x-ratelimit-limit,60",
            "content-type,application/json; charset=utf-8",
            "server,GitHub.com",
            "x-frame-options,deny"
    })

    void parameterizedTestForHeaders(String header, String expectedValue) throws IOException, InterruptedException {
        //Arrange
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URL))
                .setHeader("USER-AGENT", "JAVA 11 HTTP BOT")
                .build();
        //Act
        HttpResponse<Void> response = httpClient.send(get, HttpResponse.BodyHandlers.discarding());
        String contentType = response.headers().firstValue(header).get();
        //Assert
        Assertions.assertEquals(expectedValue,contentType);
    }

}
