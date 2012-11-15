package com.img.misc;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Set<String> segmentset=new HashSet<>();
		try(DirectoryStream<Path> dirstream = Files.newDirectoryStream(Paths.get("D:/VOCdevkit/VOC2012/SegmentationClass"))){
			for(Path filepath: dirstream){
				segmentset.add( filepath.getFileName().toString().split("\\.")[0]);
				
			}
		}
		
		try(DirectoryStream<Path> dirstream = Files.newDirectoryStream(Paths.get("D:/VOCdevkit/VOC2012/JPEGImages"))){
			Path outputfolder=Paths.get("D:/VOCdevkit/VOC2012/JPEGSegmentationClass");
			for(Path filepath: dirstream){
				String filename= filepath.getFileName().toString().split("\\.")[0];
				if(segmentset.contains(filename))
					Files.move(filepath, outputfolder.resolve(filepath.getFileName()));
			}
		}

	}

}
