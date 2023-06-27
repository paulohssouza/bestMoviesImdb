package com.paulohenrique;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
    	
    	Properties connectionProperties = new Properties();
    	try {
			FileInputStream fileInputStream = 
					new FileInputStream("C:\\Users\\paulo\\OneDrive\\Programação\\Exercícios Oracle ONE\\BackEndJava\\bestMoviesImdb\\src\\com\\paulohenrique\\connection.properties");
			connectionProperties.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	String userKeyApiImdb = connectionProperties.getProperty("userKeyApiImdb");
    	
        /*
          Connection from HTTP of IMDB 250 best movies.
         */

        String url250BestMovies = "https://imdb-api.com/en/API/Top250Movies/" + userKeyApiImdb;
        URI uri250BestMovies = URI.create(url250BestMovies);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri250BestMovies).GET().build();
        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
        String body = response.body();

        /*
          Extract the valid data
         */

        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> movieList = jsonParser.parse(body);


        /*
          View and manipulate the data
         */

        movieList.forEach((movie) -> {
        	System.out.println("250 melhores filmes:");
            System.out.println(movie.get("title"));
            System.out.println(movie.get("image"));
            System.out.println(movie.get("imDbRating"));
            System.out.println("---------------------------");
        });
    }
}