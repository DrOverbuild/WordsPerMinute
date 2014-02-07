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
	int freeTypeTime;

	public int getFreeTypeTime() {
		return freeTypeTime;
	}

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
		averageRate = Float.parseFloat(scanner.nextLine());
		highestRate = Float.parseFloat(scanner.nextLine());
		averageAccuracy = Float.parseFloat(scanner.nextLine());
		freeTypeTime = Integer.parseInt(scanner.nextLine());

	}
	public Profile(String filename) throws FileNotFoundException {
		file = Main.start.getProfile(filename);
		name = file.getName();
		Scanner scanner = new Scanner(file);
		averageRate = Float.parseFloat(scanner.nextLine());
		highestRate = Float.parseFloat(scanner.nextLine());
		averageAccuracy = Float.parseFloat(scanner.nextLine());
		freeTypeTime = Integer.parseInt(scanner.nextLine());

	}

	public void save(){
		file.delete();
		try {
			file.createNewFile();
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter(file));
				writer.write(averageRate+Main.newline+highestRate+Main.newline+averageAccuracy+Main.newline+freeTypeTime);
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

	public void setName(String name) {
		this.name = name;
	}

	public void setAverageRate(float averageRate) {
		this.averageRate = averageRate;
	}

	public void setHighestRate(float highestRate) {
		this.highestRate = highestRate;
	}

	public void setAverageAccuracy(float averageAccuracy) {
		this.averageAccuracy = averageAccuracy;
	}

	public void setFreeTypeTime(int freeTypeTime) {
		this.freeTypeTime = freeTypeTime;
	}
}
