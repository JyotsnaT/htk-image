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
		   //System.err.println(img.getType());
		   double[] colorarray=new double[3];
	        for(int y=ommit_boundary_width; y<img.getHeight()-ommit_boundary_width;y++){
	        	ScanLine line=new ScanLine();
	        	for(int x=ommit_boundary_width; x<img.getWidth()-ommit_boundary_width;x++){
	        		r.getPixel(x, y, colorarray);
	        		RegionFeature f = new RegionFeature();
	        		if (img.getType() == 5) {
    	        		if (colorarray[0]==0 && colorarray[1]==0 && colorarray[2]==0)
    	        		    f.add(0.0);
    	        		else if (colorarray[0]==128 && colorarray[1]==0 && colorarray[2]==0)
    	        		    f.add(1.0);
    	        		else if (colorarray[0]==0 && colorarray[1]==128 && colorarray[2]==0)
    	        		    f.add(2.0);
    	        		else if (colorarray[0]==128 && colorarray[1]==128 && colorarray[2]==0)
                            f.add(3.0);
    	        		else if (colorarray[0]==0 && colorarray[1]==0 && colorarray[2]==128)
                            f.add(4.0);
    	        		else if (colorarray[0]==128 && colorarray[1]==0 && colorarray[2]==128)
                            f.add(5.0);
    	        		else if (colorarray[0]==0 && colorarray[1]==128 && colorarray[2]==128)
                            f.add(6.0);
    	        		else if (colorarray[0]==128 && colorarray[1]==128 && colorarray[2]==128)
                            f.add(7.0);
    	        		else if (colorarray[0]==64 && colorarray[1]==0 && colorarray[2]==0)
                            f.add(8.0);
    	        		else if (colorarray[0]==192 && colorarray[1]==0 && colorarray[2]==0)
                            f.add(9.0);
    	        		else if (colorarray[0]==64 && colorarray[1]==128 && colorarray[2]==0)
                            f.add(10.0);
    	        		else if (colorarray[0]==192 && colorarray[1]==128 && colorarray[2]==0)
                            f.add(11.0);
    	        		else if (colorarray[0]==64 && colorarray[1]==0 && colorarray[2]==128)
                            f.add(12.0);
    	        		else if (colorarray[0]==192 && colorarray[1]==0 && colorarray[2]==128)
                            f.add(13.0);
    	        		else if (colorarray[0]==64 && colorarray[1]==128 && colorarray[2]==128)
                            f.add(14.0);
    	        		else if (colorarray[0]==192 && colorarray[1]==128 && colorarray[2]==128)
                            f.add(15.0);
    	        		else if (colorarray[0]==0 && colorarray[1]==64 && colorarray[2]==0)
                            f.add(16.0);
    	        		else if (colorarray[0]==128 && colorarray[1]==64 && colorarray[2]==0)
                            f.add(17.0);
    	        		else if (colorarray[0]==0 && colorarray[1]==192 && colorarray[2]==0)
                            f.add(18.0);
    	        		else if (colorarray[0]==128 && colorarray[1]==192 && colorarray[2]==0)
                            f.add(19.0);
    	        		else if (colorarray[0]==0 && colorarray[1]==64 && colorarray[2]==128)
                            f.add(20.0);
    	        		else
    	        		    f.add(255.0);
	        		} else {
	        		    f.add(colorarray[0]);
	        		}
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
