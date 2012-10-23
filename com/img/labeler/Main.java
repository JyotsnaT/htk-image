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
import javax.imageio.ImageIO;

import com.img.extractor.*;

public class Main {

	/**
	 * @param args <input_image_list_file> <label_output_file> [align]
	 * input_image_list is list of image to be processed
	 * output_file is the single label file resulted
	 * Usage sample: java Main imagelabel.test.scp label.mlf
	 */
	public static void main(String[] args) {
	    boolean isWriteAlignment = false;
		// Arguments
		String inFileName = args[0];
		String outFileName = args[1];
		if (args.length == 3)
		    if (args[2].equals("align")) {
		        isWriteAlignment = true;
		        System.err.println("Writing alignment info");
		    }
		
		//labeler.extractLabel(sampleSetArr, imageFileNoExt, lineNumStart)
		// Input image file list: open input file and data stream
		FileInputStream inFile;
		try {
			inFile = new FileInputStream(inFileName);
			DataInputStream inData = new DataInputStream(inFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(inData));
			
			// Give Label and output
			String line, imageFileName, classFileName;	// line, image file names and class file from list
			FeatureExtractor le = new LabelExtractor(5);
			Labeler labeler = new Labeler();
			PrintWriter out = new PrintWriter(new FileWriter(outFileName));
			out.println("#!MLF!#");
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(" ");
				imageFileName = tokens[0]; classFileName = tokens[1];
				Path classImagePath = Paths.get(classFileName);
				BufferedImage classImage = ImageIO.read(Files.newInputStream(classImagePath));
				ScanLine[] imageLines = le.extract(classImage);
				System.err.println(imageFileName);
				String labels = (isWriteAlignment ?
				        labeler.extractLabelAlignment(imageLines, imageFileName, 3) :
				            labeler.extractLabel(imageLines, imageFileName, 3));
				out.print(labels); // replace 3 with what index to start with
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
