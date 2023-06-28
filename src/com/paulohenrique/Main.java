package com.paulohenrique;


import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
    	
    	String url = "https://imdb-api.com/en/API/Top250Movies/";
    	String keyApi = "userKeyApiImdb";
    	
        /*
          Connection from HTTP of IMDB 250 best movies.
         */

    	ClientHttp clientHttp = new ClientHttp();
    	String json = clientHttp.fetchData(url, keyApi);
       
        /*
          Extract the valid data
         */

        ExtractorContent extractorContentImdb = new ExtractorContentImdb();
        List<Content> contentList = extractorContentImdb.extractorContents(json);


        /*
          View and manipulate the data
         */
        System.out.println("\u001b[1m \u001b[37m \u001b[41m"
        		+ "250 melhores filmes:\u001b[m");
        System.out.println("------------------------------------");
        contentList.forEach((content) -> {
            System.out.println(content.getTitle());
            System.out.println(content.getUrlImage());
            System.out.println("------------------------------------");
        });
        
//        GeneratorStickers generatorStickers = new GeneratorStickers();
//        
//        contentList.forEach((content) -> {
//        	String urlImageString = content.get("image");
//        	String titleFilmeString = content.get("title");
//        	InputStream inputStream = null;
//			try {
//				inputStream = new URL(urlImageString).openStream();
//			} catch (MalformedURLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//        	try {
//				generatorStickers.createSticker(inputStream, titleFilmeString);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        });
    }
}