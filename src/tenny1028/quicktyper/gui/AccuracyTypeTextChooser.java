/*
 * Copyright (c) 2014. Jasper Reddin.
 * All Rights Reserved.
 */

package tenny1028.quicktyper.gui;

import tenny1028.quicktyper.Main;
import tenny1028.quicktyper.Profile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by jasper on 5/9/14.
 */
public class AccuracyTypeTextChooser extends JFrame {

	Profile profile;

	JComboBox<String> defaultTexts = new JComboBox<String>();
	JComboBox<String> userDefinedTexts = new JComboBox<String>();
	JButton chooseDefault = new JButton("Choose Default");
	JButton chooseUserDefined = new JButton("Choose text file");
	JButton cancel = new JButton("Cancel");
	JButton chooseRandom = new JButton("Choose Random");

	public AccuracyTypeTextChooser(Profile p){
		super("Choose something to type");
		this.profile = p;

		setLayout(new BorderLayout());

		JPanel left = new JPanel();
		JPanel right = new JPanel();

		left.setLayout(new GridLayout(3,1));
		left.add(new JLabel("Choose one of the defaults..."));
		left.setBorder(new EmptyBorder(3,5,3,5));

		String[] defaultTextsNames = new String[AccuracyType.numberOfTexts];
		for(int i = 0; i<AccuracyType.numberOfTexts; i++){
			String x = "text"+i+".txt";
			defaultTextsNames[i]=x;
		}
		defaultTexts = new JComboBox<String>(defaultTextsNames);
		left.add(defaultTexts);
		left.add(chooseDefault);

		right.setLayout(new GridLayout(3,1));
		right.add(new JLabel("or choose a text file in the data folder"));
		right.setBorder(new EmptyBorder(3,5,3,5));


		File[] customTexts = Main.start.accuracyTypeTextsFolder.listFiles();
		String[] customTextsNames = new String[customTexts.length];
		int iiii = 0;
		for(File file:customTexts){
			customTextsNames[iiii] = file.getName();
			iiii++;
		}
		userDefinedTexts = new JComboBox<String>(customTextsNames);
		right.add(userDefinedTexts);
		right.add(chooseUserDefined);


		add(left, BorderLayout.WEST);
		add(right, BorderLayout.EAST);

		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottom.add(cancel);
		//bottom.add(chooseRandom);
		add(bottom,BorderLayout.SOUTH);

		chooseDefault.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int chosen = defaultTexts.getSelectedIndex();
				String text = Main.getTXTFileInJar("/accuracytypetexts/text" + chosen + ".txt");
				new AccuracyType(profile, text);
				closeWindow();
			}
		});

		chooseUserDefined.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int chosen = userDefinedTexts.getSelectedIndex();
				String filename = userDefinedTexts.getItemAt(chosen);
				String text = null;
				try {
					text = Main.getTXTFileNotInJar(new File(Main.start.accuracyTypeTextsFolder.getAbsolutePath() + Main.fileSeparator + filename));
				} catch(FileNotFoundException e1) {
					text = "Error";
				}
				new AccuracyType(profile,text);
				closeWindow();
			}
		});

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		pack();
		setMinimumSize(getSize());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);

	}

	@Override
	public void dispose(){
		super.dispose();
		new ProfileViewer(profile);
	}

	public void closeWindow(){
		super.dispose();
	}

}
