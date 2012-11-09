package com.img.extractor;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.transform.DctNormalization;
import org.apache.commons.math3.transform.FastCosineTransformer;
import org.apache.commons.math3.transform.RealTransformer;
import org.apache.commons.math3.transform.TransformType;

public class CommonUtils {
    public static RealTransformer transformer=new FastCosineTransformer(DctNormalization.STANDARD_DCT_I);
	public static double[][][] img2Array(BufferedImage img, ColorSpaceConverter colorConverter) {
		// TODO Auto-generated method stub
		double[][][] output=new double[img.getHeight()][img.getWidth()][];
		WritableRaster r = img.getRaster();
		double[] temp=new double[3];
		for(int x=0; x<img.getWidth();x++)
			for(int y=0; y<img.getHeight();y++){
				r.getPixel(x, y, temp);
				output[y][x]=colorConverter.convert(temp);
			}
		return output;
	}

	

	public static RegionFeature cosineTransform3Channel(double[][][] region, int[] featuresize) {
		// TODO Auto-generated method stub
		RegionFeature output=new RegionFeature();
		
		insertcosineTransformData(0, region, output, featuresize[0]);
		insertcosineTransformData(1, region, output, featuresize[1]);
		insertcosineTransformData(2, region, output, featuresize[2]);
		
		return output;
	}
	
	public static void insertcosineTransformData(int index, double[][][] region, RegionFeature featureset, int featuresize) {
		double[][] c0 = getChannel(index, region);
		RealMatrix m0=new Array2DRowRealMatrix(c0);
		transform2D(m0, transformer);
		
		double[][] data = m0.getData();
	    for(int i=0; i<data.length; i++ )
	    	for(int j=0; j<data[i].length; j++)
		     {
			   if(i+j<featuresize)
				featureset.add(data[i][j]);
		     }
		
		
	}
	
	
	public static void transform2D(RealMatrix region, RealTransformer transform) {
		// TODO Auto-generated method stub
		for(int i=0; i<region.getRowDimension();i++){
		  double[] c = transform.transform(region.getRow(i), TransformType.FORWARD);
		  region.setRow(i, c);
		}
		for(int i=0; i<region.getColumnDimension();i++){
			  double[] c = transform.transform(region.getColumn(i), TransformType.FORWARD);
			  region.setColumn(i, c);
		}
		
	}
	public static double[][] getChannel(int index, double[][][] region){
		double[][] output=new double[region.length][];
		for(int i=0; i<output.length; i++){
			double[] vector=new double[region[i].length];
			for(int j=0; j<vector.length;j++)
				vector[j]=region[i][j][index];
			output[i]=vector;
		}
		return output;
	}

}
