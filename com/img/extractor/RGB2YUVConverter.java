package com.img.extractor;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

public class RGB2YUVConverter implements ColorSpaceConverter{
	static RealMatrix YUV =
		    new Array2DRowRealMatrix(new double[][] { {0.2126, 0.7152, 0.0722}, 
		    		                                  {-0.09991, -0.33609, 0.436  }, 
		    		                                  {0.615, -0.55861, -0.05639  } 
		    		                                   },
		                       false);//YUV color space BT 709
  public double[] convert(double[] rgb){
	  return YUV.operate(rgb);
	  
  }
}
