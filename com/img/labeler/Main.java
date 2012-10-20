package com.img.labeler;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.img.extractor.*;

public class Main {

	/**
	 * @param args input_image_list_file label_output_file
	 * input_image_list is list of image to be processed
	 * output_file is the single label file resulted
	 */
	public static void main(String[] args) {
		// Arguments
		String inFileName = args[0];
		String outFileName = args[1];
		
		// Initialize label mapping
		HashMap<RegionFeature, String> labelMap = new HashMap<RegionFeature, String>();
		
		RegionFeature tmp = new RegionFeature();
		// TODO: map pixel colors to transcription label 0 to 20, change the values and duplicate line
		tmp.add(0.0); tmp.add(0.0); tmp.add(0.0);
		labelMap.put(tmp, "0");
		tmp.clear();
		// TODO: continue
		
		
		
		//labeler.extractLabel(sampleSetArr, imageFileNoExt, lineNumStart)
		// Input image file list: open input file and data stream
		FileInputStream inFile;
		try {
			inFile = new FileInputStream(inFileName);
			DataInputStream inData = new DataInputStream(inFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(inData));
			
			// Give Label and output
			String fileName;	// buffered file names from list
			FeatureExtractor le = new LabelExtractor(5);
			Labeler labeler = new Labeler(labelMap);
			PrintWriter out = new PrintWriter(new FileWriter(outFileName));
			out.println("#!MLF!#");
			while ((fileName = br.readLine()) != null) {
				Path imagePath = Paths.get(fileName);
				BufferedImage image = ImageIO.read(Files.newInputStream(imagePath));
				ScanLine[] imageLines = le.extract(image);
				labeler.extractLabel(imageLines, fileName.split("\\.")[0], 3); // replace 3 with what index to start with
			}
			out.close(); br.close(); inData.close(); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
