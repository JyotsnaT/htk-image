package com.img.extractor;

import java.awt.image.BufferedImage;


public class DCTExtractor implements FeatureExtractor {
	//Note that the first floor(size/2) rows and columns are ommitted
    int neigbourDistance;
    int size;
    ColorSpaceConverter colorConverter=new RGB2YUVConverter();
    
	public DCTExtractor(int size){
		neigbourDistance=size/2;
		this.size=size;
	}
	
	@Override
	public ScanLine[]  extract(BufferedImage img) {
	   double[][][] imgarray=CommonUtils.img2Array(img, colorConverter);
	   ScanLine[] output=new ScanLine[img.getHeight()-size+1];
	   
        for(int y=neigbourDistance; y<img.getHeight()-neigbourDistance;y++){
        	ScanLine line=new ScanLine();
        	for(int x=neigbourDistance; x<img.getWidth()-neigbourDistance;x++){
        		double[][][] region=new double[size][size][];
        		for(int i=0; i<size;i++)
        			for(int j=0; j<size;j++)
        				region[i][j]=imgarray[y-neigbourDistance+j][x-neigbourDistance+i];
        		line.add(CommonUtils.cosineTransform3Channel(region));
        			
        	}
        	output[y-neigbourDistance]=line;
        }		
		return output;
	}
    
	

}
