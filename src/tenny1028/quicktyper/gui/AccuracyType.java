/*
 * Copyright (c) 2014. Jasper Reddin.
 * All Rights Reserved.
 */

package tenny1028.quicktyper.gui;

import tenny1028.quicktyper.Main;
import tenny1028.quicktyper.Profile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jasper on 3/20/14.
 */
public class AccuracyType extends JFrame {

	Profile currentProfile;

	public ArrayList<Character> everyChar;
	public ArrayList<JLabel> everyCharLabel;

	/**
	 * Holds the number of errors user made while typing.
	 */
	public int errors;

	public AccuracyType(Profile currentProfile){
		super("Accuracy Type");

		this.currentProfile = currentProfile;

		// Read from the file, then put each character in the ArrayList
		everyChar = new ArrayList<>();
		InputStream sample = Main.class.getResourceAsStream("/sample.txt");
		Scanner sampleScanner = new Scanner(sample);
		sampleScanner.useDelimiter("");
		while(sampleScanner.hasNext()){
			char idk = sampleScanner.next().charAt(0);
			everyChar.add(new Character(idk));
		}

		everyCharLabel = new ArrayList<>();
		for(Character c : everyChar){
			everyCharLabel.add(new JLabel(c.toString()));
		}

		setLayout(new BorderLayout());
		final JTextArea area = new JTextArea(20,40);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(area);

		area.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				e.consume();
				char c = e.getKeyChar();
				try{
					if(c == area.getText().charAt(area.getCaretPosition())){
						area.setCaretPosition(area.getCaretPosition()+1);
						area.select(0,area.getCaretPosition());
					}else{
						errors++;
					}
				}catch(StringIndexOutOfBoundsException e2){
					JOptionPane.showMessageDialog(null,"Errors: " + errors);
					String percentage = formatToPercent(getAccuracyPercentage(errors, area.getText().length()));
					JOptionPane.showMessageDialog(null, "Accuracy: " + percentage);
					errors = 0;
					area.setCaretPosition(0);
					dispose();
					new ProfileViewer(getCurrentProfile());
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

		String buffer1 = "";
		for(Character c : everyChar){
			buffer1 += (c.toString());
		}
		area.setText(buffer1);

		add(scrollPane, BorderLayout.CENTER);

		area.setCaretPosition(0);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		pack();


	}

	public float getAccuracyPercentage(int errors, int total){
		float correct = (float) total - (float) errors;
		return correct / (float) total;
	}

	public Profile getCurrentProfile(){
		return currentProfile;
	}

	public String formatToPercent(float accuracy){
		accuracy *= 100;

		BigDecimal bd = new BigDecimal(Float.toString(accuracy));
		bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
		return "" + bd.floatValue() + "%";
	}
}
