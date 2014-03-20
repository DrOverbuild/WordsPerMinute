/*
 * Copyright (c) 2014. Jasper Reddin.
 * All Rights Reserved.
 */

package tenny1028.quicktyper.gui;

import tenny1028.quicktyper.Profile;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by jasper on 2/7/14.
 */
public class ProfileOptions extends JFrame {

	Profile profile;

	JSpinner freeTypeTimeSpinner;

	public ProfileOptions(Profile prof){
		super("Profile Options");
		profile = prof;

		setLayout(new GridLayout(3,1));

		JPanel renamePanel = new JPanel(new BorderLayout(5,5));
		renamePanel.add(new JLabel("Name of profile: "),BorderLayout.WEST);
		final JTextField nameField = new JTextField(profile.getName(),20);
		renamePanel.add(nameField,BorderLayout.CENTER);
		add(renamePanel);

		JPanel panel1 = new JPanel(new BorderLayout(5,5));
		panel1.add(new JLabel("Free Type Time in seconds:"),BorderLayout.WEST);
		freeTypeTimeSpinner = new JSpinner();
		freeTypeTimeSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				profile.setFreeTypeTime((int) freeTypeTimeSpinner.getValue());
			}
		});
		panel1.add(freeTypeTimeSpinner, BorderLayout.CENTER);
		add(panel1);

		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton resetStatsButton = new JButton("Reset Profile");
		resetStatsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int confirmDialog = JOptionPane.showConfirmDialog(null, "Are you sure? This cannot be undone.");
				if(confirmDialog == 0) profile.reset();
				freeTypeTimeSpinner.setValue(120);
			}
		});
		panel2.add(resetStatsButton);
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				profile.setName(nameField.getText());
				profile.save();
				new ProfileViewer(profile);
				dispose();
			}
		});
		panel2.add(closeButton);
		add(panel2);

		freeTypeTimeSpinner.setValue(profile.getFreeTypeTime());

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		setMinimumSize(getSize());
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
