package com.img.htk.hmm;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ModelCopy {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
        Path hmmfile=Paths.get("D:","hmmdef.txt");      
        System.out.println(replicateHMM(hmmfile, new String[]{"1","2"}));
        
        
	}
	public static String replicateHMM(Path source, String[] name) throws IOException{
		StringBuilder sb=new StringBuilder();
        try(BufferedReader reader=Files.newBufferedReader(source, Charset.defaultCharset())){        	
        	String line=reader.readLine();
        	while(line!=null){
        	  sb.append(line).append("\r\n");
        	  line=reader.readLine();
        	}
        }
        String model=sb.toString();
        StringBuilder output=new StringBuilder();
        for(String n: name){
        	output.append("~h \"").append(n).append("\"\r\n");
        	output.append(model);
        }
		return output.toString();
		
	}

}
