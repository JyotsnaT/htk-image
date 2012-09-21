package com.img.extractor;

import java.awt.image.BufferedImage;
import java.util.List;

public interface FeatureExtractor {
   List<Double> extract(BufferedImage img);
}
