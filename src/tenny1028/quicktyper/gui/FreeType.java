/*
 * Copyright (c) 2014. Jasper Reddin.
 * All Rights Reserved.
 */

package tenny1028.quicktyper.gui;

import tenny1028.quicktyper.Main;
import tenny1028.quicktyper.Profile;
import tenny1028.quicktyper.Start;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jasper on 2/2/14.
 */
public class FreeType extends JFrame implements KeyListener, ActionListener {
	Timer timer;
	Profile profile;
	JTextArea area;
	boolean timerRunning=false;
	JLabel messages;
	int secondsLeft;

	public FreeType(Profile profile1){
		super("Free Type");

		this.profile = profile1;
		secondsLeft = profile.getFreeTypeTime();

		setLayout(new BorderLayout(5,5));

		area = new JTextArea(20,30);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.addKeyListener(this);
		add(new JScrollPane(area),BorderLayout.CENTER);

		messages = new JLabel("Begin typing to start timer.");
		JPanel bottom = new JPanel(new BorderLayout(5,5));
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				dispose();
				new ProfileViewer(profile);
			}
		});
		bottom.add(messages, BorderLayout.LINE_START);
		bottom.add(cancel,BorderLayout.LINE_END);
		add(bottom, BorderLayout.PAGE_END);

		timer = new Timer(1000,this);
		timer.setRepeats(true);

		pack();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public float getWordsPerMinute(){
		int numberOfWords = area.getText().split(" ").length;
		float seconds = profile.getFreeTypeTime()-secondsLeft;
		float minutes = seconds/60;

		return numberOfWords/minutes;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(!timerRunning){
			timer.start();
			timerRunning = true;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(secondsLeft != 0){
			if(secondsLeft == 10){
				messages.setFont(new Font(messages.getFont().getFontName(),Font.BOLD,messages.getFont().getSize()));
				messages.setForeground(Color.red);
			}
			messages.setText(secondsLeft+" seconds left; " + getWordsPerMinute() + "wpm");
		}else{
			endGame();
		}
		secondsLeft--;

	}
	public void endGame(){
		timer.stop();
		area.setEditable(false);
		messages.setText("0 seconds left");
		timerRunning = false;
		float wordsperminute = getWordsPerMinute();
		if((profile.getHighestRate()<wordsperminute)&&!Float.isInfinite(wordsperminute)){
			profile.setHighestRate(wordsperminute);
		}
		JOptionPane.showMessageDialog(null,"Timer done.");
		JOptionPane.showMessageDialog(null,area.getText().split(" ").length + " words; " + wordsperminute + "wpm");
		int userWantsToSave = JOptionPane.showConfirmDialog(null,"Do you want to save the text you wrote?", "", JOptionPane.YES_NO_OPTION);
		if (userWantsToSave == 0){
			//BufferedWriter writer = null;
			//try {
				String filename = new SimpleDateFormat("MM-dd-yyyy").format(new Date()) + " at " + new SimpleDateFormat("hh:mm:ss a").format(new Date()) + ".txt";

				/*
				File save = new File(profile.getSaves().getAbsolutePath() + Main.fileSeparator + ;
				writer = new BufferedWriter(new FileWriter(lastLoginFile.getAbsolutePath()));
				writer.write(filepath);
				writer.flush();
				previouslyOpenedProfile = new File(filepath);

				// Create saves folder
				String withOutFileExtension = previouslyOpenedProfile.getName().substring(0,previouslyOpenedProfile.getName().length()-11);
				File profileSavesFolder = new File(savesFolder.getAbsolutePath()+ Main.fileSeparator+withOutFileExtension);
				profileSavesFolder.mkdir();
				writer.close();
				*/
			//} catch(IOException e) {
			//}
			System.out.println(filename);
		}
		dispose();
		profile.setAverageRate(Start.averageOf(new float[]{profile.getAverageRate(), wordsperminute}));
		new ProfileViewer(profile);
	}
}
