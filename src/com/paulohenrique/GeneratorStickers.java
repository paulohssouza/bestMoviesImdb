package com.paulohenrique;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GeneratorStickers {

	void createSticker() throws IOException {
		// reading image
		
		BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\paulo"
				+ "\\OneDrive\\Programação"
				+ "\\Exercícios Oracle ONE\\BackEndJava\\bestMoviesImdb"
				+ "\\src\\images\\filme.jpg"));
		
		//create new image
		
		Integer width = originalImage.getWidth();
		Integer height = originalImage.getHeight();
		Integer newHeight = height + 200;
		BufferedImage newImage = new BufferedImage(width, newHeight, 
				BufferedImage.TRANSLUCENT);
		
		
		//copy original image to new image
		
		Graphics2D graphics2d = (Graphics2D) newImage.getGraphics();
		graphics2d.drawImage(originalImage, 0, 0, null);
		
		//Add phrase to image
		
		//Save new image to a file
		ImageIO.write(newImage, "png", new File("C:\\Users\\paulo\\OneDrive"
				+ "\\Programação\\Exercícios Oracle ONE\\BackEndJava"
				+ "\\bestMoviesImdb\\src\\images\\sticker.png"));
		
	}
	
	public static void main(String[] args) throws IOException {
		GeneratorStickers stickers = new GeneratorStickers();
		stickers.createSticker();
	}
	
}
