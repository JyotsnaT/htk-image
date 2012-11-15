package com.img.misc;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TrainValidateTestData {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Path validate=Paths.get("D:/Img/PlaneValidate");
		Path test=Paths.get("D:/Img/PlaneTest");
		List<Path> segmentset=new ArrayList<>();
		try(DirectoryStream<Path> dirstream = Files.newDirectoryStream(Paths.get("D:/Img/PlaneTrain"))){
			for(Path filepath: dirstream){
				segmentset.add(filepath);				
			}
		}
		Path filepath;
		Collections.shuffle(segmentset);
        int test_validate_size=segmentset.size()/10;
        for(int i=0; i<2*test_validate_size; i+=2 ){
        	filepath=segmentset.get(i);
        	Files.move(filepath, validate.resolve(filepath.getFileName()));
        	filepath=segmentset.get(i+1);
        	Files.move(filepath, test.resolve(filepath.getFileName()));
         }
    }
}


