package com.img.htk.hmm;

import java.util.Arrays;

public class RegularState implements HMMState {
	int state;
	double[] mean;
	double[] v;
    public RegularState(int statenum, int vectorsize){
    	state=statenum;
    	mean=new double[vectorsize];
    	Arrays.fill(mean, 0);
    	v=new double[vectorsize];   	
    	Arrays.fill(v, 0);
    }
    public String toString(){
    	StringBuilder sb=new StringBuilder();
		sb.append("<State> ").append(state).append("\r\n");		
		sb.append("<Mean> ").append(mean.length).append("\r\n");
		for(double miu: mean){			
			   sb.append(" ").append(miu);
		}
		sb.append("\r\n");
		sb.append("<Variance> ").append(v.length).append("\r\n");
		for(double variance: v){			
			   sb.append(" ").append(variance);
		}
		
		return sb.toString();
    	
    }
}
