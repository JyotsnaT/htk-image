package com.img.extractor;

import java.awt.image.BufferedImage;
import java.util.Arrays;


public class DCTExtractor implements FeatureExtractor {
	//Note that the first floor(size/2) rows and columns are ommitted
    int neigbourDistance;
    int size;
    int[] featuresize; //only x+y<= featuresize will be keep, so it should ranges from 0 to 2*(size-1)
    ColorSpaceConverter colorConverter=new RGB2YUVConverter();
    
	public DCTExtractor(int size){
		neigbourDistance=size/2;
		this.size=size;
		featuresize=new int[3];
		featuresize[0]=size-1;
		featuresize[1]=size-2;
		featuresize[2]=size-2;
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
        		line.add(CommonUtils.cosineTransform3Channel(region, featuresize));
        			
        	}
        	output[y-neigbourDistance]=line;
        }		
		return output;
	}
    
	

}
