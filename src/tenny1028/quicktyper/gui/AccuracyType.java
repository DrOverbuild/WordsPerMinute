/*
 * Copyright (c) 2014. Jasper Reddin.
 * All Rights Reserved.
 */

package tenny1028.quicktyper.gui;

import tenny1028.quicktyper.Main;
import tenny1028.quicktyper.Profile;
import tenny1028.quicktyper.Start;
import tenny1028.quicktyper.gui.caret.BlockStyleCaret;
import tenny1028.quicktyper.gui.util.StaticMethods;
import tenny1028.quicktyper.timer.StopwatchTimer;
import tenny1028.quicktyper.timer.StopwatchTimerAction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by jasper on 3/20/14.
 */
public class AccuracyType extends JFrame {

	Profile currentProfile;


	/**
	 * Holds the number of errors user made while typing.
	 */
	public int errors;

	/**
	 * EVERY TIME A TEXT IS ADDED TO src/accuracytypetexts/, THIS INT SHOULD BE INCREMENTED! THIS INT STORES THE NUMBER
	 * OF FILES SO THE RANDOM NUMBER GENERATOR CAN SELECT A RANDOM FILE IN THE RESOURCE STREAM.
	 */
	public static final int numberOfTexts = 2;

	public JLabel errorsL = new JLabel("Errors: 0");
	public JLabel elapsedTime = new JLabel("Time: 0s");
	public JTextArea area = new JTextArea(20,40);

	StopwatchTimer timer;
	boolean timerIsRunning;

	public AccuracyType(Profile currentProfile, String text){
		super("Accuracy Type");

		this.currentProfile = currentProfile;

		/*Random r = new Random();
		int rr = r.nextInt(numberOfTexts);
		String areaText = Main.getTXTFileInJar("/accuracytypetexts/text"+rr+".txt");*/

		area.setText(text);

		setLayout(new BorderLayout());
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(area);

		area.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				e.consume();
				if(e.getKeyChar() == KeyEvent.VK_ESCAPE){
					dispose();
					return;
				}
				char c = e.getKeyChar();
				try{
					if(c == area.getText().charAt(area.getCaretPosition())){
						area.setCaretPosition(area.getCaretPosition()+1);
						//area.select(0,area.getCaretPosition());
					}else{
						errors++;
						errorsL.setText("Errors: " + errors);
						errorsL.setForeground(Color.RED);
						//area.setCaretPosition(area.getCaretPosition()+1);
					}
				}catch(StringIndexOutOfBoundsException e2){ // This exception is thrown when user gets done typing.
					close();
				}
				if(!timerIsRunning){
					timer.start();
					timerIsRunning = true;
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				e.consume();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				e.consume();
			}
		});

		add(scrollPane, BorderLayout.CENTER);

		area.setCaret(new BlockStyleCaret());
		area.getCaret().setBlinkRate(0);
		area.setFont(new Font(Font.MONOSPACED,Font.PLAIN,14));
		area.setCaretPosition(0);

		JPanel panel = new JPanel(new BorderLayout());
		errorsL.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		elapsedTime.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
		panel.add(errorsL, BorderLayout.WEST);
		panel.add(elapsedTime,BorderLayout.EAST);
		panel.setBorder(new EmptyBorder(1,5,1,5));
		add(panel, BorderLayout.NORTH);

		timer = new StopwatchTimer(new StopwatchTimerAction() {
			@Override
			public void forEverySecond(StopwatchTimer timer) {
				int seconds = timer.getElapsedTime();
				elapsedTime.setText("Time: "+seconds+"s");
			}
		});

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		pack();


	}

	public float getAccuracyFloat(int errors, int total){
		float correct = (float) total - (float) errors;
		return correct / (float) total;
	}

	public Profile getCurrentProfile(){
		return currentProfile;
	}

	public float formatToPercent(float accuracy){
		accuracy *= 100;
		return StaticMethods.roundTo(accuracy,2);
	}
	/**
	 * Ends the typing and returns to profile viewer. <em>DOES NOT TERMINATE USER.</em> Seriously, why would it terminate
	 * user? If it did then it would be illegal.
	 */
	public void close(){
		float wpms = getWordsPerMinute();
		float accuracy = formatToPercent(getAccuracyFloat(errors, area.getText().length()));

		if((currentProfile.getHighestRate()<wpms)&&!Float.isInfinite(wpms)){
			currentProfile.setHighestRate(wpms);
		}
		currentProfile.registerAccuracy(accuracy);
		currentProfile.registerRate(wpms);

		timer.reset();
		timerIsRunning = true;
		JOptionPane.showMessageDialog(null, "Accuracy: " + accuracy + "%, Typing Rate: " + wpms + "wpm");
		errors = 0;
		dispose();
	}

	/**
	 * Disposes and closes the window, but also returns to the profile viewer.
	 */
	@Override
	public void dispose(){
		super.dispose();
		new ProfileViewer(currentProfile);
	}

	public float getWordsPerMinute(){
		return StaticMethods.getWordsPerMinute(area.getText(),timer.getElapsedTime());
	}
}
