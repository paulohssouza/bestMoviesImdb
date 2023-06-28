package com.paulohenrique;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtractorContentImdb implements ExtractorContent{
	
	public List<Content> extractorContents(String json) {
		JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> attributesList = jsonParser.parse(json);
        
        List<Content> contentList = new ArrayList<Content>();
        
        attributesList.forEach((attribute) -> {
        	String title = attribute.get("title");
        	String urlImage = attribute.get("image")
        			.replaceAll("(@+)(.*).jpg$", "$1.jpg");
        	Content content = new Content(title, urlImage);
        	contentList.add(content);
        });
        
        return contentList;
	}

}
