package com.paulohenrique;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeneratorStickers {

	void createSticker(InputStream inputStream, String fileName) throws IOException {
		// reading image
		
		BufferedImage originalImage = ImageIO.read(inputStream);
		
		//create new image
		
		Integer width = originalImage.getWidth();
		Integer height = originalImage.getHeight();
		Integer newHeight = height + 200;
		BufferedImage newImage = new BufferedImage(width, newHeight, 
				BufferedImage.TRANSLUCENT);
		
		
		//copy original image to new image
		
		Graphics2D graphics2d = (Graphics2D) newImage.getGraphics();
		graphics2d.drawImage(originalImage, 0, 0, null);
		
		//Configure font size
		
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 200);
		graphics2d.setFont(font);
		graphics2d.setColor(Color.RED);
		
		//Add phrase to image
		
		graphics2d.drawString("Topzera", 200, newHeight - 40);
		
		//Save new image to a file
		ImageIO.write(newImage, "png", new File("C:\\Users\\paulo\\OneDrive"
				+ "\\Programação\\Exercícios Oracle ONE\\BackEndJava"
				+ "\\bestMoviesImdb\\src\\images\\" + fileName + ".png"));
		
	}
	
}
