package com.img.labeler;

import java.util.HashMap;

import com.img.extractor.*;

public class Labeler {
	private HashMap<RegionFeature, String> labelMap;
	
	public Labeler(HashMap<RegionFeature, String> lmap) {
		labelMap = lmap;
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
