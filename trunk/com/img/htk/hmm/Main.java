package com.img.htk.hmm;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TransMatrix tm=new RegularTransMatrix(3);
        HMM hmm=new HMM(tm);
        hmm.addState(new RegularState(1, 5));
        hmm.addState(new RegularState(2, 5));
        hmm.addState(new RegularState(3, 5));
        System.out.println(hmm);
	}

}
