/*
 * Copyright (c) 2014. Jasper Reddin.
 * All Rights Reserved.
 */

package tenny1028.quicktyper.gui.util;

import tenny1028.quicktyper.Start;

import java.math.BigDecimal;

/**
 * Created by jasper on 6/8/14.
 */
public class StaticMethods {

	public static float getWordsPerMinute(String words, int seconds){
		float minutes = seconds / 60.0f;
		return roundTo((words.length()/5) / minutes, 2);
	}

	public static float roundTo(float f,int decimalPlaces){
		try{
			BigDecimal bd = new BigDecimal(f);
			bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
			return bd.floatValue();
		}catch(NumberFormatException e){
			return 0;
		}
	}

	public static float averageOf(float[] floats){
		float total = 0.0f;
		for(int i = 0; i<floats.length;i++){
			total += floats[i];
		}
		return total/floats.length;
	}
}
