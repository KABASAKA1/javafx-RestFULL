package com.javafx_restfull.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class ApiService {

    private final HttpClient httpClient;

    public ApiService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public <T> CompletableFuture<T> sendRequest(String url, String method, String body, TypeReference<T> responseType) {
        HttpRequest request = buildRequest(url, method, body);

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    try {
                        return parseResponse(response, responseType);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private HttpRequest buildRequest(String url, String method, String body) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url));

        switch (method.toUpperCase()) {
            case "GET":
                builder.GET();
                break;
            case "POST":
                builder.POST(HttpRequest.BodyPublishers.ofString(body));
                break;
            case "PUT":
                builder.PUT(HttpRequest.BodyPublishers.ofString(body));
                break;
            case "DELETE":
                builder.DELETE();
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }

        return builder.build();
    }

    private <T> T parseResponse(HttpResponse<String> response, TypeReference<T> responseType) throws IOException {
        if (response.statusCode() != 200) {
            throw new IOException("Error: HTTP status code " + response.statusCode());
        }

        String responseBody = response.body();
        try {
            // Use a JSON library (e.g., Jackson) to parse JSON responses
            // Replace with your actual JSON parsing code
            return new ObjectMapper().readValue(responseBody, responseType);
        } catch (IOException e) {
            throw new IOException("Error parsing JSON: " + e.getMessage());
        }
    }
}
