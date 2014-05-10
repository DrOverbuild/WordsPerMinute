/*
 * Copyright (c) 2014. Jasper Reddin.
 * All Rights Reserved.
 */

package tenny1028.quicktyper.gui;

import tenny1028.quicktyper.Main;
import tenny1028.quicktyper.Profile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Collections;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jasper on 5/9/14.
 */
public class AccuracyTypeTextChooser extends JFrame {

	Profile profile;

	JComboBox<String> defaultTexts;
	JComboBox<String> userDefinedTexts;
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
		defaultTexts = new JComboBox<>(defaultTextsNames);
		left.add(defaultTexts);
		left.add(chooseDefault);

		right.setLayout(new GridLayout(3,1));
		right.add(new JLabel("or choose a text file in the data folder"));
		right.setBorder(new EmptyBorder(3,5,3,5));


		File[] customTexts = Main.start.accuracyTypeTextsFolder.listFiles();
		String[] customTextsNames = new String[]{};
		List<String> theSameThingAsCustomTextsNamesButItIsAListInsteadOfAnArray = new ArrayList<>();
		for(File file:customTexts){
			if(!file.isHidden()){
				theSameThingAsCustomTextsNamesButItIsAListInsteadOfAnArray.add(file.getName());
			}
		}
		customTextsNames = theSameThingAsCustomTextsNamesButItIsAListInsteadOfAnArray.toArray(customTextsNames);

		userDefinedTexts = new JComboBox<>(customTextsNames);
		right.add(userDefinedTexts);
		right.add(chooseUserDefined);


		add(left, BorderLayout.WEST);
		add(right, BorderLayout.EAST);

		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottom.add(cancel);
		bottom.add(chooseRandom);
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

		chooseRandom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] texts = getAllAvailableTexts();
				Random r = new Random();
				new AccuracyType(profile, texts[r.nextInt(texts.length)]);
				closeWindow();
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

	/**
	 * Grabs all text files that are available in the jar file and all the files
	 * available in the accuracy type data folder on the user's computer.
	 * @return An array of strings, each string containing the contents of one of
	 * the files
	 */
	public String[] getAllAvailableTexts(){
		File[] customTexts = Main.start.accuracyTypeTextsFolder.listFiles();
		List<File> itsAListBecauseILikeLists = new ArrayList<>();
		for(File file:customTexts){
			if(!file.isHidden()){
				itsAListBecauseILikeLists.add(file);
			}
		}

		String[] texts = new String[itsAListBecauseILikeLists.size()+AccuracyType.numberOfTexts];
		int iiii = 0;
		for(File file:itsAListBecauseILikeLists){
			try {
				texts[iiii] = Main.getTXTFileNotInJar(file);
			} catch(FileNotFoundException e) {
				texts[iiii] = "Error";
			}
			iiii++;
		}

		for(int i = 0; i<AccuracyType.numberOfTexts; i++){
			String x = Main.getTXTFileInJar("/accuracytypetexts/text"+i+".txt");
			texts[i+iiii]=x;
		}

		return texts;

	}
}
