package com.img.extractor;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in)){
			String filepath = sc.next();
			FeatureExtractor fe=new DCTExtractor(5); //let the size of the block be 5x5.
		    Path path = Paths.get(filepath);
		    try(InputStream is=Files.newInputStream(path)){
		    	BufferedImage image = ImageIO.read(is);
		    	ScanLine[] features = fe.extract(image); //Note that the first floor(size/2) rows and columns are ommitted
		    	System.out.println(features[0]); 
		    } catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}

}
