package com.img.labelconverter;

public class InvalidLabelException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public InvalidLabelException(String msg) {
        System.err.println("Invalid label file: " + msg); // followed by newline or message
    }

}
