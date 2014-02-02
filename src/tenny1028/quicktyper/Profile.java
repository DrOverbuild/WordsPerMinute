/*
 * Copyright (c) 2014. Jasper Reddin.
 * All Rights Reserved.
 */

package tenny1028.quicktyper;

import java.io.*;
import java.util.Scanner;

/**
 * Created by jasper on 1/31/14.
 */
public class Profile {
	File file;
	String name;
	float averageRate;
	float highestRate;
	float averageAccuracy;

	public String getName() {
		return name;
	}

	public float getAverageRate() {
		return averageRate;
	}

	public float getHighestRate() {
		return highestRate;
	}

	public float getAverageAccuracy() {
		return averageAccuracy;
	}

	public Profile(File file) throws FileNotFoundException {
		this.file = file;
		name = file.getName();
		Scanner scanner = new Scanner(file);
		averageRate = scanner.nextFloat();
		highestRate = scanner.nextFloat();
		averageAccuracy = scanner.nextFloat();

	}
	public Profile(String filename) throws FileNotFoundException {
		file = Main.getProfile(filename);
		name = file.getName();
		Scanner scanner = new Scanner(file);
		averageRate = Float.parseFloat(scanner.nextLine());
		highestRate = Float.parseFloat(scanner.nextLine());
		averageAccuracy = Float.parseFloat(scanner.nextLine());

	}

	public void save(){
		file.delete();
		try {
			file.createNewFile();
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter(file));
				writer.write(averageRate+Main.newline+highestRate+Main.newline+averageAccuracy);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					writer.close();
				} catch (Exception e) {
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}

	}
}
