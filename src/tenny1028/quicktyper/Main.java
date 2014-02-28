/*
 * Copyright (c) 2014. Jasper Reddin.
 * All Rights Reserved.
 */

package tenny1028.quicktyper;
public class Main{
	public static Start start;
	public static final String newline = System.getProperty("line.separator");
	public static final String fileSeparator = System.getProperty("file.separator");
	public static void main(String[] args){
		start = new Start();
		start.start(args);
	}
}