package com.img.scriptcreator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // Arguments
        String rootDirName = args[0];
        String outScriptName = args[1];
        
        File rootDir = new File(rootDirName);
        String[] featureFoldersList = rootDir.list();
        try {
            PrintWriter out = new PrintWriter(new FileWriter(outScriptName));
            for (int i = 0; i < featureFoldersList.length; i++) {
                File featDir = new File(rootDirName + "/" + featureFoldersList[i]);
                String[] featureContentsList = featDir.list();
                for (int j = 0; j < featureContentsList.length; j++)
                     out.println(rootDirName + "/" + featureFoldersList[i] + "/" + featureContentsList[j]);
            }
            out.close();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}
