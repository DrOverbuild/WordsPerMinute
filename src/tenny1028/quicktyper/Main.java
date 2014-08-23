/*
 * Copyright (c) 2014. Jasper Reddin.
 * All Rights Reserved.
 */

package tenny1028.quicktyper;

import tenny1028.quicktyper.exceptions.FileAlreadyExistsException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
	public static Start start;
	public static final String newline = System.getProperty("line.separator");
	public static final String fileSeparator = System.getProperty("file.separator");
	public static void main(String[] args){
		start = new Start();
		start.start(args);
	}

	public static String getTXTFileInJar(String fileName){
		// Read from the file, then put each character in the ArrayList
		// We won't need it now, but we might, so go make a sandwich while you wait.
        // Update: We are using this method in class HelpSection. I hope your sandwich was good.
		String everyChar = "";
		InputStream sample = Main.class.getResourceAsStream(fileName);
		if(sample == null){
			System.out.println("Resource not found.");
			return "Error";
		}
		Scanner sampleScanner = new Scanner(sample);
		sampleScanner.useDelimiter("");
		while(sampleScanner.hasNext()){
			everyChar += sampleScanner.next().charAt(0);
		}

		return everyChar.replaceAll("#ACCURACY_TYPE_DATA_FOLDER", start.accuracyTypeTextsFolder.getAbsolutePath());
	}

	public static String getTXTFileNotInJar(File filename) throws FileNotFoundException {
		String everyChar = "";
		Scanner sampleScanner = new Scanner(filename);
		sampleScanner.useDelimiter("");
		while(sampleScanner.hasNext()){
			everyChar += sampleScanner.next().charAt(0);
		}

		return everyChar;
	}
}