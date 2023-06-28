package com.paulohenrique;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Properties;

public class ClientHttp {
	
	public String fetchData(String urlApi, String keyProperties) {
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
    	
    	try {
    		String userKeyApiImdb = connectionProperties.getProperty(keyProperties);
    		
    		String url250BestMovies = urlApi 
            		+ userKeyApiImdb;
            URI uri250BestMovies = URI.create(url250BestMovies);
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(uri250BestMovies)
            		.GET().build();
            HttpResponse<String> response = httpClient.send(request, 
            		BodyHandlers.ofString());
            String body = response.body();
            
            return body;
            
		} catch (IOException | InterruptedException exception) {
			throw new RuntimeException(exception);
		}
        
	}

}
