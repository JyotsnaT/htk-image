package com.img.labeler;

import java.util.HashMap;
import com.img.extractor.*;

public class Labeler {
	private HashMap<Double, Character> labelMap;	// RegionFeature element to alphabet
	
	public Labeler() {
		// Fill in table to translate floating point from RegionFeature to applicable alphabet
		labelMap = new HashMap<Double, Character>();
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
			ret += ("\"" + imageFileNoExt + String.format("/line%04d.lab", lineNum) + "\"\n");
			ret += extractLabel(line);	// newline already written
			ret += ".\n";
			lineNum++;
		}
		return ret;
	}
	
	/**
	 * Extract label for each pixel on a given line
	 * @param sampleSet line sample containing 3-element RegionFeature vectors, corresponding to R, G, B
	 */
	public String extractLabel(ScanLine sampleSet) {
		String ret = "";
		for (RegionFeature featureVector : sampleSet) {
			ret += labelMap.get(featureVector.get(0));
			ret += "\n";
		}
		return ret;
	}
	
	/**
     * Extract label for each pixel range (aligned) on a given line but
     * @param sampleSet line sample containing 3-element RegionFeature vectors, corresponding to R, G, B
     */
    public String extractLabelAlignment(ScanLine[] sampleSetArr, String imageFileNoExt, int lineNumStart) {
        String ret = "";
        int lineNum = lineNumStart;
        for (ScanLine line : sampleSetArr) {
            ret += ("\"" + imageFileNoExt + String.format("/line%04d.lab", lineNum) + "\"\n");
            ret += extractLabelAlignment(line);  // newline already written
            ret += ".\n";
            lineNum++;
        }
        return ret;
    }
	
	/**
     * Extract label for each pixel range (aligned) on a given line but
     * @param sampleSet line sample containing 3-element RegionFeature vectors, corresponding to R, G, B
     */
    public String extractLabelAlignment(ScanLine sampleSet) {
        // Initialization
        int periodSize = 100000;
        int alignStart = 0; int alignEnd = 1;
        Character curr = labelMap.get(sampleSet.get(0).get(0));
        Character read;
        String ret = "";
        
        for (int i = 1; i < sampleSet.size(); i++) {
            read = labelMap.get(sampleSet.get(i).get(0));
            if (read == curr) // no file write if same, defer until it is different
                alignEnd++;
            else {
                ret += ((alignStart * periodSize) + " " + (alignEnd * periodSize) + " " + curr + "\n");
                alignStart = alignEnd; alignEnd = alignStart + 1;
                curr = read;
            }
        }
        ret += ((alignStart * periodSize) + " " + (alignEnd * periodSize) + " " + curr + "\n");
        
        return ret;
    }
}
