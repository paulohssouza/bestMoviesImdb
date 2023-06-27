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
					new FileInputStream
					("C:\\Users\\paulo\\OneDrive\\Programação\\"
							+ "Exercícios Oracle ONE\\BackEndJava\\"
							+ "bestMoviesImdb\\src\\com\\paulohenrique\\"
							+ "connection.properties");
			connectionProperties.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	String userKeyApiImdb = connectionProperties.getProperty("userKeyApiImdb");
    	
        /*
          Connection from HTTP of IMDB 250 best movies.
         */

        String url250BestMovies = "https://imdb-api.com/en/API/Top250Movies/" 
        		+ userKeyApiImdb;
        URI uri250BestMovies = URI.create(url250BestMovies);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri250BestMovies)
        		.GET().build();
        HttpResponse<String> response = httpClient.send(request, 
        		BodyHandlers.ofString());
        String body = response.body();
        
        String url250BestSeriesTv = "https://imdb-api.com/en/API/Top250TVs/" 
        		+ userKeyApiImdb;
        URI uri250BestSeriesTv = URI.create(url250BestSeriesTv);
        HttpClient httpClient2 = HttpClient.newHttpClient();
        HttpRequest request2 = HttpRequest.newBuilder(uri250BestSeriesTv)
        		.GET().build();
        HttpResponse<String> response2 = httpClient2.send(request2, 
        		BodyHandlers.ofString());
        String body2 = response2.body();

        /*
          Extract the valid data
         */

        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> movieList = jsonParser.parse(body);
        List<Map<String, String>> serieList = jsonParser.parse(body2);


        /*
          View and manipulate the data
         */
        System.out.println("\u001b[1m \u001b[37m \u001b[41m"
        		+ "250 melhores filmes:\u001b[m");
        System.out.println("------------------------------------");
        movieList.forEach((movie) -> {
            System.out.println(movie.get("title"));
            System.out.println(movie.get("image"));
            System.out.println(movie.get("imDbRating"));
            System.out.println("------------------------------------");
        });
        
        System.out.println("\u001b[1m \u001b[37m \u001b[41m"
        		+ "250 melhores séries:\u001b[m");
        System.out.println("------------------------------------");
        serieList.forEach((serie) -> {
            System.out.println(serie.get("title"));
            System.out.println(serie.get("image"));
            System.out.println(serie.get("imDbRating"));
            System.out.println("-------------------------------------");
        });
    }
}