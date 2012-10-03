package com.img.binarizer;

public class Auxiliary {
	public Auxiliary() {}
	
	/*
	 * Tokenize line from text file
	 */
	public String[] TokenizeLine(String line, String delim, int expectedSize)
			throws InconsistentVectorSizeException {
		String[] tokens = line.split(delim);
		if (expectedSize == 0 || expectedSize == tokens.length) {}
		else throw new InconsistentVectorSizeException(line);
		return tokens;
	}
	
	/*
	 * Convert integer to byte
	 */
	public byte[] ConvertToByte(int n) { // buffer must be initialized
		byte[] b = new byte[4];
		b[0] = (byte) (n >>> 24);
		b[1] = (byte) (n >>> 16);
		b[2] = (byte) (n >>> 8);
		b[3] = (byte) n;
		return b;
	}
	
	/*
	 * Convert short to byte
	 */
	public byte[] ConvertToByte(short n) { // buffer must be initialized
		byte[] b = new byte[2];
		b[0] = (byte) (n >>> 8);
		b[1] = (byte) n;
		return b;
	}
	
	/*
	 * Convert float to byte
	 */
	public byte[] ConvertToByte(float f) { // buffer must be initialized
		int n = Float.floatToIntBits(f);
		byte[] b = new byte[4];
		b[0] = (byte) (n >>> 24);
		b[1] = (byte) (n >>> 16);
		b[2] = (byte) (n >>> 8);
		b[3] = (byte) n;
		return b;
	}
}
