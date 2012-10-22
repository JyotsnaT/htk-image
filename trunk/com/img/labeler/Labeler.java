package com.img.labeler;

import java.util.HashMap;

import com.img.extractor.*;

public class Labeler {
	private HashMap<Double, Character> labelMap;	// RegionFeature element to alphabet
	
	public Labeler() {
		// Fill in table to translate floating point from RegionFeature to applicable alphabet
		labelMap = new HashMap<>();
		labelMap.put(0.0, 'A');		// background
		labelMap.put(1.0, 'B');		// airplane
		labelMap.put(2.0, 'C');		// bicycle
		labelMap.put(3.0, 'D');		// bird
		labelMap.put(4.0, 'E');		// boat
		labelMap.put(5.0, 'F');		// bottle
		labelMap.put(6.0, 'G');		// bus
		labelMap.put(7.0, 'H');		// car
		labelMap.put(8.0, 'I');		// cat
		labelMap.put(9.0, 'J');		// chair
		labelMap.put(10.0, 'K');	// cow
		labelMap.put(11.0, 'L');	// dining table
		labelMap.put(12.0, 'M');	// dog
		labelMap.put(13.0, 'N');	// horse
		labelMap.put(14.0, 'O');	// motorcycle
		labelMap.put(15.0, 'P');	// person
		labelMap.put(16.0, 'Q');	// potted plant
		labelMap.put(17.0, 'R');	// sheep
		labelMap.put(18.0, 'S');	// sofa
		labelMap.put(19.0, 'T');	// train
		labelMap.put(20.0, 'U');	// TV/monitor
		labelMap.put(255.0, 'Z');	// void
	}
	
	/**
	 * Extract label for a single image
	 * @param sampleSetArr representation of multiple lines on a file
	 * @param imageFileNoExt image complete file name (including path, but without extension) or prefix folder name
	 * @param lineNumStart line number to start with
	 */
	public String extractLabel(ScanLine[] sampleSetArr, String imageFileNoExt, int lineNumStart) {
		String ret = "";
		int lineNum = lineNumStart;
		for (ScanLine line : sampleSetArr) {
			ret += ("\"" + imageFileNoExt + "\\line" + String.format("line%04d.lab", lineNum) + "\"\n");
			ret += extractLabel(line);	// newline already written
			lineNum++;
		}
		return ret;
	}
	
	/**
	 * Extract label for each pixel on a given line
	 * @param sampleSet line sample containing 3-element RegionFeature vectors, corresponding to R, G, B
	 * @param labelMap fixed mapping between color characteristic to its corresponding label
	 */
	public String extractLabel(ScanLine sampleSet) {
		String ret = "";
		for (RegionFeature featureVector : sampleSet) {
			ret += labelMap.get(featureVector);
			ret += "\n";
		}
		return ret;
	}
}
