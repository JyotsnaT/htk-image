package com.img.misc;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.img.extractor.RegionFeature;
import com.img.extractor.ScanLine;

public class Label2Img {

	/**
	 * @param args <label_file>.mlf <output_folder>
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String labelfilepath=args[0];
		String outputfolder=args[1];
		int padding=2;
		Path labelfile=Paths.get(labelfilepath);
		String imgname=null;
		
		try(BufferedReader br=Files.newBufferedReader(labelfile, Charset.defaultCharset())){
			String line=br.readLine();
			List<ScanLine> compressimg=new ArrayList<>();
			 
			while(line!=null){
			  if(line.startsWith("\"")){
				  line=line.substring(1, line.length()-1);
				  Path imgpath=Paths.get(line);
				  String current_img=imgpath.getFileName().toString().substring(0, 11);
				  if(imgname==null){
					  imgname=current_img;
				  }
				  if(current_img.equals(imgname)){
					  addLine(br, compressimg);					  					  
				  }else{
					  BufferedImage img=convertToImg(compressimg, padding);
					  try(OutputStream os=Files.newOutputStream(Paths.get(outputfolder, imgname+"label.png"))){
						  ImageIO.write(img, "png", os);
					  }
					  imgname=current_img;
					  compressimg.clear();
					  addLine(br, compressimg);
				  }
					  
				  
			  }
				  
			  line=br.readLine();
			  
			}
			BufferedImage img=convertToImg(compressimg, padding);
			  try(OutputStream os=Files.newOutputStream(Paths.get(outputfolder, imgname+".png"))){
				  ImageIO.write(img, "png", os);
			}
			
		}
		
		
				
	}

	private static void addLine(BufferedReader br, List<ScanLine> compressimg) throws IOException {
		// TODO Auto-generated method stub
		ScanLine compressline=new ScanLine();
		double start, end;
		double color;
		String line;
		RegionFeature feature;
		do{					    
			line=br.readLine();					    
		    if(!line.equals(".")){
		    	String[] element=line.split("\\s");
		    	start=Double.parseDouble(element[0])/100000;
		    	end=Double.parseDouble(element[1])/100000;
		    	color=Utils.labelMap.get(element[2].charAt(0));
		    	feature=new RegionFeature();
		    	feature.add(start);
		    	feature.add(end);
		    	feature.add(color);
		    	compressline.add(feature);
		    }					    
		  }while(!line.equals("."));
		  compressimg.add(compressline);
		
	}

	private static BufferedImage convertToImg(List<ScanLine> compressimg, int padding) {
		// TODO Auto-generated method stub
		Double[][] imgarray=new Double[compressimg.size()][];
		for(int i=0; i<compressimg.size(); i++ ){
			ScanLine line=compressimg.get(i);
			List<Double> line_class=new ArrayList<>();
			for(RegionFeature feature: line){
				double start=feature.get(0);
				double end=feature.get(1);
				double c=feature.get(2);
				for(;start<end;start++){
					line_class.add(c);
				}
			}
			imgarray[i]=line_class.toArray(new Double[0]);
		}
		BufferedImage img=new BufferedImage(imgarray[0].length+2*padding, imgarray.length+2*padding, BufferedImage.TYPE_INT_RGB);
		WritableRaster r = img.getRaster();
		int[] color;
		for(int y=0; y<imgarray.length; y++){
			for(int x=0; x<imgarray[y].length; x++){
				color=Utils.LableColorMap.get(imgarray[y][x]);
				r.setPixel(x+padding, y+padding, color);
			}
		}
		
		return img;
	}

}
