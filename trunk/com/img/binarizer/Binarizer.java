package com.img.binarizer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

import com.img.extractor.*;

class InconsistentVectorSizeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	public InconsistentVectorSizeException(String input) {
		super();
		System.err.println(input + ": inconsistent vector size");
	}
}

public class Binarizer {

	/**
	 * @param args fieldSeparator inputFile outputFile
	 * running: java Binarizer.class fieldSeparator inputFile outputFile
	 */
	/*public static void main(String[] args) {
		// With no arguments = show usage
		if (args.length < 3) ShowUsage();
		
		// Main loop
		String delim = args[0];
		String inFileName = args[1];
		String outFileName = args[2];
		Auxiliary aux = new Auxiliary();
		try {
			// Open input file and data stream
			FileInputStream inFile = new FileInputStream(inFileName);
			DataInputStream inData = new DataInputStream(inFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(inData));
			
			// Open output file
			RandomAccessFile outFile = new RandomAccessFile(outFileName, "rw");
			outFile.seek(12);	// skip header
			String[] tokens;
			String line;
			int vecSize = new Integer(0); // initialized to 0
			int sampCount = 0;
			while ((line = br.readLine()) != null) {
				tokens = aux.TokenizeLine(line, delim, vecSize);
				vecSize = tokens.length;
				for (int i = 0; i < tokens.length; i++) {
					byte[] floatBytes = aux.ConvertToByte(Float.parseFloat(tokens[i]));
					outFile.write(floatBytes);
				}
				sampCount++;
			}
			outFile.seek(0);
			byte[] buffer = aux.ConvertToByte(sampCount); outFile.write(buffer);
			buffer = aux.ConvertToByte((int) 100000); outFile.write(buffer);
			buffer = aux.ConvertToByte((short) (4 * vecSize)); outFile.write(buffer);
			buffer = aux.ConvertToByte((short) 9); outFile.write(buffer);	// USER
			outFile.close(); inData.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InconsistentVectorSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public void binarize(ScanLine sampleSet, String outFileName) {
		// With no arguments = show usage
		
		// Main loop
		Auxiliary aux = new Auxiliary();
		try {
			// Open output file
			RandomAccessFile outFile = new RandomAccessFile(outFileName, "rw");
			outFile.seek(12);	// skip header
			int vecSize = new Integer(0); // initialized to 0
			int sampCount = 0;
			//while ((line = br.readLine()) != null) {
			for (RegionFeature featureVector : sampleSet) {
				if (vecSize == 0) vecSize = featureVector.size();
				else if (vecSize != featureVector.size()) {
					outFile.close();
					throw new InconsistentVectorSizeException(featureVector.toString());
				}
				for (Double val : featureVector) {
					byte[] floatBytes = aux.ConvertToByte(val.floatValue());
					outFile.write(floatBytes);
				}
				sampCount++;
			}
			outFile.seek(0);
			byte[] buffer = aux.ConvertToByte(sampCount); outFile.write(buffer);
			buffer = aux.ConvertToByte((int) 100000); outFile.write(buffer);
			buffer = aux.ConvertToByte((short) (4 * vecSize)); outFile.write(buffer);
			buffer = aux.ConvertToByte((short) 9); outFile.write(buffer);	// USER
			outFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InconsistentVectorSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void ShowUsage() {
		System.err.println("Usage:");
		System.err.println("\tjava Binarizer <field separator> <input file name> <output file name>");
		System.err.println("\t<field separator>  : a specific character to split the values in each sample vector");
		System.err.println("\t<input file name>  : file name of input file (plain text with numbers)");
		System.err.println("\t<output file name> : where to save the output binary");
		System.err.println("Each sample vector in the input plain text file must be separated by new line");
	}
}


