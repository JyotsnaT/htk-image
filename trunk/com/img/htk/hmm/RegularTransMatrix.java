package com.img.htk.hmm;

import java.util.Arrays;

public class RegularTransMatrix implements TransMatrix {
	double[][] transMatrix;
	public RegularTransMatrix(int size){
		transMatrix=new double[size][size];
		for(int i=0; i<size; i++){
			Arrays.fill(transMatrix[i], 0);
			if(i==0){
				transMatrix[i][1]=1;
				continue;
			}
			if(i+1<size){
			 transMatrix[i][i]=0.5;			
			 transMatrix[i][i+1]=0.5;
			}
		}
	}
	public String toString(){
		StringBuilder sb=new StringBuilder();
				
		sb.append("<TransP> ").append(transMatrix.length).append("\r\n");
		for(double[] line: transMatrix){
			for(double t: line)
			   sb.append(" ").append(t);
			sb.append("\r\n");
		}
		
		
		return sb.toString();
	}

}
