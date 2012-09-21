package com.img.extractor;

import java.awt.image.BufferedImage;

public interface FeatureExtractor {
	ScanLine[]  extract(BufferedImage img);
}
