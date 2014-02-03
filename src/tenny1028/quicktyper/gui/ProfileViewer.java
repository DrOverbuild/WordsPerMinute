/*
 * Copyright (c) 2014. Jasper Reddin.
 * All Rights Reserved.
 */

package tenny1028.quicktyper.gui;

import tenny1028.quicktyper.Main;
import tenny1028.quicktyper.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jasper on 1/31/14.
 */
public class ProfileViewer extends JFrame {

	Profile profile;

	public ProfileViewer(Profile profile){

		this.profile = profile;
		Main.start.currentlyOpenProfile = profile;
		createGUIComponents();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setMinimumSize(getSize());
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public void createGUIComponents(){
		setTitle("Profile");
		setLayout(new BorderLayout());
		addLabels();
		add(addButtons(),BorderLayout.PAGE_END);


	}

	public void addLabels(){
		JPanel statsLabels = new JPanel(new GridLayout(4,1,4,4));
		JPanel stats = new JPanel(new GridLayout(4,1,4,4));
		Font boldFont = new Font(Font.DIALOG,Font.BOLD,12);

		JLabel statsLabel = new JLabel("Stats",JLabel.CENTER);
		statsLabel.setFont(boldFont);
		add(statsLabel,BorderLayout.PAGE_START);

		JLabel profileLabel = new JLabel("  Profile: ");
		JLabel averageRateLabel = new JLabel("  Average Rate: ");
		JLabel highestRateLabel = new JLabel("  Highest Rate: ");
		JLabel averageAccuracyLabel = new JLabel("  Average Accuracy: ");
		profileLabel.setFont(boldFont);
		averageRateLabel.setFont(boldFont);
		highestRateLabel.setFont(boldFont);
		averageAccuracyLabel.setFont(boldFont);

		statsLabels.add(profileLabel);
		statsLabels.add(averageRateLabel);
		statsLabels.add(highestRateLabel);
		statsLabels.add(averageAccuracyLabel);

		add(statsLabels,BorderLayout.WEST);

		JLabel prof = new JLabel(profile.getName()+"  ",JLabel.RIGHT);
		JLabel averageRate = new JLabel(profile.getAverageRate() + "wpm  ",JLabel.RIGHT);
		JLabel hightestRate = new JLabel(profile.getHighestRate() + "wpm  ",JLabel.RIGHT);
		JLabel averageAccuracy = new JLabel(profile.getAverageAccuracy() + "%  ",JLabel.RIGHT);

		stats.add(prof);
		stats.add(averageRate);
		stats.add(hightestRate);
		stats.add(averageAccuracy);

		add(stats,BorderLayout.EAST);
	}

	public JPanel addButtons(){
		JPanel panel = new JPanel(new BorderLayout());
		JPanel twoByTwo = new JPanel(new GridLayout(2,2));


		// Initialize buttons
		JButton freeType = new JButton("Free Type");
		JButton accuracyType = new JButton("Accuracy Type");
		JButton profOptions = new JButton("Profile Options");
		JButton switchProfile = new JButton("Switch Profile");
		JButton help = new JButton("Help");

		// Action Listeners
		freeType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new FreeType(profile);
				close();
			}
		});
		accuracyType.addActionListener(null);
		profOptions.addActionListener(null);
		switchProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ProfileChooser();
				close();
			}
		});
		help.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new HelpSection();
			}
		});

		// Add buttons to panel
		twoByTwo.add(freeType);
		twoByTwo.add(accuracyType);
		twoByTwo.add(profOptions);
		twoByTwo.add(switchProfile);

		// Add components to bigger panel
		panel.add(twoByTwo,BorderLayout.PAGE_START);
		panel.add(help,BorderLayout.PAGE_END);

		// Return
		return panel;
	}

	public void close(){
		profile.save();
		dispose();
	}
}
