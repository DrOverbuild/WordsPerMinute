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



	/**
	 * Holds the free typing text that the user typed and saved.
	 */
	File saves;
	String name;
	float averageRate;
	float highestRate;
	float averageAccuracy;
	int freeTypeTime;

	public File getSaves() {
		return saves;
	}

	public File getFile() {
		return file;
	}

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
		this.saves = new File(Main.start.savesFolder.getAbsolutePath()+Main.fileSeparator+file.getName().substring(0,file.getName().length()-11));
		name = file.getName();
		Scanner scanner = new Scanner(file);
		averageRate = Float.parseFloat(scanner.nextLine());
		highestRate = Float.parseFloat(scanner.nextLine());
		averageAccuracy = Float.parseFloat(scanner.nextLine());
		freeTypeTime = Integer.parseInt(scanner.nextLine());

	}
	public Profile(String filename) throws FileNotFoundException {
		file = Main.start.getProfile(filename);
		this.saves = new File(Main.start.savesFolder.getAbsolutePath()+Main.fileSeparator+file.getName().substring(0,file.getName().length()-11));
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

	public void reset(){
		setAverageRate(0f);
		setHighestRate(0f);
		setAverageAccuracy(0f);
		setFreeTypeTime(120);
		save();
	}

	public void setName(String name) {
		this.name = name;
		if (name.endsWith(".wpmprofile")){
			file.renameTo(new File (file.getParentFile().getAbsolutePath() + Main.fileSeparator + name));
			saves.renameTo(new File(saves.getParentFile().getAbsolutePath() + Main.fileSeparator + name.substring(0,name.length()-11)));
		} else{
			file.renameTo(new File (file.getParentFile().getAbsolutePath() + Main.fileSeparator + name + ".wpmprofile"));
			saves.renameTo(new File(saves.getParentFile().getAbsolutePath() + Main.fileSeparator + name));
		}


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

	public void setSaves(File saves) {
		this.saves = saves;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
