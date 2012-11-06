package com.img.htk.hmm;

import java.util.ArrayList;
import java.util.List;

public class HMM {
    
    List<HMMState> s=new ArrayList<HMMState>();
	TransMatrix transP;
	public HMM(TransMatrix m){
		transP=m;
	}
	public  void addState(HMMState state){
		s.add(state);
	}
	public void setTransMatrix(TransMatrix m){
		transP=m;
	}
	public String toString(){
		StringBuilder sb=new StringBuilder();
		sb.append("<BeginHMM>\r\n");		
		sb.append("<NumStates> ").append(s.size()).append("\r\n");
		for(HMMState state: s){
			if(!(state instanceof NonEmittingState))
			   sb.append(state).append("\r\n");
		}
		sb.append(transP);
		sb.append("<EndHMM>\r\n");
		return sb.toString();
		
	}

}
