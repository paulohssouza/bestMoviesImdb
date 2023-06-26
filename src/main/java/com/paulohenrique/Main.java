package com.paulohenrique;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        /*
          Conexion from HTTP of IMDB 250 best movies.
         */

        String url250BestMovies = "https://imdb-api.com/en/API/Top250Movies/k_45r7fn7q";
        URI uri250BestMovies = URI.create(url250BestMovies);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri250BestMovies).GET().build();
        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        /*
          Extract the valid data
         */

        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> movieList = jsonParser.parse(body);

        /*
          View and manipulate the data
         */
    }
}