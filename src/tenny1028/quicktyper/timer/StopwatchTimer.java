/*
 * Copyright (c) 2014. Jasper Reddin.
 * All Rights Reserved.
 */

package tenny1028.quicktyper.timer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Used to measure elapsed time in seconds from one point to another.
 */
public class StopwatchTimer extends Timer implements ActionListener{

	/**
	 * Stores the elapsed time (in seconds) from when the timer was started.
	 */
	int elapsedTime;

	StopwatchTimerAction action;

	public StopwatchTimer(StopwatchTimerAction action) {
		super(1000, null);

		this.action = action;
		elapsedTime = 0;

		addActionListener(this);

	}

	public void reset(){
		stop();
		elapsedTime = 0;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		elapsedTime++;
		action.forEverySecond(this);
	}

	public int getElapsedTime(){

		return elapsedTime;
	}
}
