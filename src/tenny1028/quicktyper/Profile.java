/*
 * Copyright (c) 2014. Jasper Reddin.
 * All Rights Reserved.
 */

package tenny1028.quicktyper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by jasper on 1/31/14.
 */
public class Profile {
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
		name = file.getName();
		Scanner scanner = new Scanner(file);
		averageRate = scanner.nextFloat();
		highestRate = scanner.nextFloat();
		averageAccuracy = scanner.nextFloat();

	}
	public Profile(String filename) throws FileNotFoundException {
		File thefile = Main.getProfile(filename);
		name = thefile.getName();
		Scanner scanner = new Scanner(thefile);
		averageRate = Float.parseFloat(scanner.nextLine());
		highestRate = Float.parseFloat(scanner.nextLine());
		averageAccuracy = Float.parseFloat(scanner.nextLine());

	}
}
