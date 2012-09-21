package com.img.extractor;

import java.awt.image.BufferedImage;
import java.util.List;

public interface FeatureExtractor {
   List<List<Double>> extract(BufferedImage img);//Each inner list represents 1 frame.
}
