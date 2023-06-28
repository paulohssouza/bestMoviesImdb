package com.paulohenrique;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
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

        /*
          Extract the valid data
         */

        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> contentList = jsonParser.parse(body);


        /*
          View and manipulate the data
         */
        System.out.println("\u001b[1m \u001b[37m \u001b[41m"
        		+ "250 melhores filmes:\u001b[m");
        System.out.println("------------------------------------");
        contentList.forEach((content) -> {
            System.out.println(content.get("title"));
            System.out.println(content.get("image"));
            System.out.println(content.get("imDbRating"));
            System.out.println("------------------------------------");
        });
        
        GeneratorStickers generatorStickers = new GeneratorStickers();
        
        contentList.forEach((content) -> {
        	String urlImageString = content.get("image");
        	String titleFilmeString = content.get("title");
        	InputStream inputStream = null;
			try {
				inputStream = new URL(urlImageString).openStream();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        	try {
				generatorStickers.createSticker(inputStream, titleFilmeString);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    }
}