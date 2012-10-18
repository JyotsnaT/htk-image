package com.img.binarizer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.img.extractor.DCTExtractor;
import com.img.extractor.FeatureExtractor;
import com.img.extractor.ScanLine;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(Scanner sc=new Scanner(System.in)){
			String filepath = sc.next();
			int size=5;
			FeatureExtractor fe=new DCTExtractor(5); //let the size of the block be 5x5.
		    Path path = Paths.get(filepath);
		    Path outputfolder=Paths.get("D:", path.getFileName().toString().split("\\.")[0]);// The "D:" can be change to any absolute path of a folder. The outputfolder is this case is D:\[filename]
		   
		    try(InputStream is=Files.newInputStream(path)){
		    	Files.createDirectories(outputfolder);
		    	BufferedImage image = ImageIO.read(is);
		    	ScanLine[] features = fe.extract(image); //Note that the first floor(size/2) rows and columns are ommitted
		    	
		    	
		    	
		    	Binarizer bn=new Binarizer();
		    	int startlineNum=1+size/2;//calculate the ommited boundary.
		    	for(int i=0; i<features.length; i++){	//each line become one file.
		    	 Path pix=Paths.get(outputfolder.toString(), String.format("line%d.pix", i+startlineNum));
		    	 bn.binarize(features[i], pix.toString());
		    	}
		    	
		    } catch (IOException e) {
				
				e.printStackTrace();
			}
		}

	}

}