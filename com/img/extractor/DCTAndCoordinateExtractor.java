package com.img.extractor;

import java.awt.image.BufferedImage;


public class DCTAndCoordinateExtractor implements FeatureExtractor {
	//Note that the first floor(size/2) rows and columns are ommitted
	static final int normalize_factor=10; 
	static final int center=5; 
    int neigbourDistance;
    int size;
    int[] featuresize; //only x+y<= featuresize will be keep, so it should ranges from 0 to 2*(size-1)
    ColorSpaceConverter colorConverter=new RGB2YUVConverter();
    
	public DCTAndCoordinateExtractor(int size){
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
	   double normal_y;
	   double normal_x;
	   double central_distance;
	   
        for(int y=neigbourDistance; y<img.getHeight()-neigbourDistance;y++){
        	ScanLine line=new ScanLine();
        	for(int x=neigbourDistance; x<img.getWidth()-neigbourDistance;x++){
        		double[][][] region=new double[size][size][];
        		for(int i=0; i<size;i++)
        			for(int j=0; j<size;j++)
        				region[i][j]=imgarray[y-neigbourDistance+j][x-neigbourDistance+i];
        		RegionFeature feature = CommonUtils.cosineTransform3Channel(region, featuresize);
        		normal_y=((double)y*normalize_factor)/img.getHeight();
        		normal_x=((double)x*normalize_factor)/img.getWidth();
        		central_distance=Math.pow(normal_x-center, 2)+Math.pow(normal_y-center, 2);
        		feature.add(normal_y);
        		feature.add(central_distance);
        		line.add(feature);
        			
        	}
        	output[y-neigbourDistance]=line;
        }		
		return output;
	}
    
	

}
