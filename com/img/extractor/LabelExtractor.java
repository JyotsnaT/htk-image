package com.img.extractor;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class LabelExtractor implements FeatureExtractor {
	int ommit_boundary_width;
	int size;
	
	public LabelExtractor(int block_size){
		size=block_size;
		ommit_boundary_width=size/2;
	}
	@Override
	public ScanLine[] extract(BufferedImage img) {
		   
		   ScanLine[] output=new ScanLine[img.getHeight()-size+1];
		   WritableRaster r = img.getRaster();
		   double[] colorarray=new double[3];
	        for(int y=ommit_boundary_width; y<img.getHeight()-ommit_boundary_width;y++){
	        	ScanLine line=new ScanLine();
	        	for(int x=ommit_boundary_width; x<img.getWidth()-ommit_boundary_width;x++){
	        		r.getPixel(x, y, colorarray);
	        		RegionFeature f = new RegionFeature();
	        		f.add(colorarray[0]); // Q: is this only red? We need to specify the G and B also
	        		line.add(f);	        			
	        	}
	        	output[y-ommit_boundary_width]=line;
	        }		
			return output;
	}

	/**
	 * @param args
	 */
	

}
