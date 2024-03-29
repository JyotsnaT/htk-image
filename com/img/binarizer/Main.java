package com.img.binarizer;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

import com.img.extractor.DCTAndCoordinateExtractor;
import com.img.extractor.DCTExtractor;
import com.img.extractor.FeatureExtractor;
import com.img.extractor.ScanLine;

public class Main {

	/**
	 * @param args <inFileName> [coord]
	 * <inFileName>: name of file containing list of image files to be processed
	 * coord: means that y-coordinate and distance from center will be included in extraction
	 * Usage sample: java Main binarize.train.scp [coord]
	 */
    public static void main(String[] args) {
        // Arguments processing
        String inFileName = args[0];
        boolean isWithCoord = false;
        if (args.length > 1)
            isWithCoord = args[1].equals("coord");
        
        // Binarizing: Feature Extraction
        try {
            FileInputStream inFile = new FileInputStream(inFileName);
            DataInputStream inData = new DataInputStream(inFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(inData));
            String line, filepath;        // line buffer, image file path
            Path outputfolder;
            int blockSize=5;    // block dimension for pixel feature extraction: 5 x 5
            Binarizer bn = new Binarizer();
            // Loop over the files to binarize image
            while ((line = br.readLine()) != null) {
                // line pre-processing
                String[] tokens = line.split(" ");
                filepath = tokens[0]; outputfolder = Paths.get(tokens[1]); 
                FeatureExtractor fe = (isWithCoord ?
                        new DCTAndCoordinateExtractor(blockSize) : new DCTExtractor(blockSize));
                Path path = Paths.get(filepath);
                
                // image file processing
                InputStream is=Files.newInputStream(path);
                Files.createDirectories(outputfolder);
                BufferedImage image = ImageIO.read(is);
                ScanLine[] features = fe.extract(image); // Note that the first floor(size/2) rows and columns are omitted
                int startlineNum = 1 + blockSize/2; // calculate the omitted boundary
                for (int i = 0; i < features.length; i++) {
                    // each line become one file.
                    Path pix=Paths.get(outputfolder.toString(), String.format("line%04d.pix", i+startlineNum));
                    bn.binarize(features[i], pix.toString());
                }
            }
            br.close();
		} catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

	}

}
