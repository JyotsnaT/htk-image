package com.img.labelconverter;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

    /**
     * Usage: java Main <aligned_label_file> <output_pixel_label_file>
     * <aligned_label_file> is the input label file with alignment (number) information
     * <output_pixel_label_file> is the output label file with each pixel labeled individually
     * Post-processing step of decoding output for pixel-to-pixel comparison in HResults
     * @param args <aligned_label_file> <output_pixel_label_file>
     */
    public static void main(String[] args) {
        // Arguments
        String alignedFileName = args[0];
        String outFileName = args[1];
        
        try {
            FileInputStream alignedFile = new FileInputStream(alignedFileName);
            DataInputStream alignedData = new DataInputStream(alignedFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(alignedData));
            if (!br.readLine().equals("#!MLF!#")) {
                br.close();
                throw new InvalidLabelException("#!MLF!# header expected");
            }
            
            PrintWriter out = new PrintWriter(new FileWriter(outFileName));
            out.println("#!MLF!#");
            String line;    // line buffer
            int transcLen;  // length of transcription
            int periodSize = 100000;    // pre-defined sampling/transcription period
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(" ");  // tokenize
                if (tokens.length == 1)
                    out.println(line);
                else if (tokens.length >= 3) {
                    transcLen = (Integer.parseInt(tokens[1]) - Integer.parseInt(tokens[0])) / periodSize;
                    for (int i = 0; i < transcLen; i++)
                        out.println(tokens[2]);
                }
            }
            out.close(); br.close(); alignedData.close(); alignedFile.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidLabelException e) {
            e.printStackTrace();
        }
        
    }

}
