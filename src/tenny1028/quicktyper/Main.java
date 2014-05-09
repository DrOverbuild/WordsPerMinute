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
		ArrayList<Character> everyChar = new ArrayList<>();
		InputStream sample = Main.class.getResourceAsStream(fileName);
		if(sample == null){
			System.out.println("Resource not found.");
			return "Error";
		}
		Scanner sampleScanner = new Scanner(sample);
		sampleScanner.useDelimiter("");
		while(sampleScanner.hasNext()){
			char idk = sampleScanner.next().charAt(0);
			everyChar.add(new Character(idk));
		}

		String builder = "";

		for(Character character: everyChar){
			builder += character; // I tried the concat method, but doesn't work, returns an empty string in return statement below.
		}

		return builder;
	}

	public static String getTXTFileNotInJar(File filename) throws FileNotFoundException {
		ArrayList<Character> everyChar = new ArrayList<>();
		Scanner sampleScanner = new Scanner(filename);
		sampleScanner.useDelimiter("");
		while(sampleScanner.hasNext()){
			char idk = sampleScanner.next().charAt(0);
			everyChar.add(new Character(idk));
		}

		String builder = "";

		for(Character character: everyChar){
			builder += character; // I tried the concat method, but doesn't work, returns an empty string in return statement below.
		}

		return builder;
	}
}