/*
 * Copyright (c) 2014. Jasper Reddin.
 * All Rights Reserved.
 */

package tenny1028.quicktyper.gui;

import tenny1028.quicktyper.Profile;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jasper on 1/31/14.
 */
public class ProfileViewer extends JFrame {

	Profile profile;

	public ProfileViewer(Profile profile){

		this.profile = profile;
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

}
