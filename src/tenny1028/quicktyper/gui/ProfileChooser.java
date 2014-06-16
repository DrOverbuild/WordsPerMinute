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
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by jasper on 1/28/14.
 */
public class ProfileChooser extends JFrame{

	final JComboBox<String> jComboBox;

	public ProfileChooser(){
		File[] allProfiles = Main.start.profilesDirectory.listFiles();
		String[] allProfilesNames = new String[allProfiles.length];
		for(int i = 0; i < allProfiles.length;i++){
			allProfilesNames[i]=allProfiles[i].getName();
		}
		jComboBox = new JComboBox<String>(allProfilesNames);
		JLabel label = new JLabel("Choose profile to open: ");
		JButton button1 = new JButton("Choose");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Main.start.openProfile((String) jComboBox.getSelectedItem());
					new ProfileViewer(new Profile((String) jComboBox.getSelectedItem()));
					dispose();
				} catch(FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		JButton button2 = new JButton("New Profile");
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = Main.start.createProfileGUI();
				jComboBox.addItem(s+".wpmprofile");
				pack();
				jComboBox.setSelectedItem(s+".wpmprofile");
			}
		});
		JButton button3 = new JButton("Delete Selected Profile");
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int bool = JOptionPane.showConfirmDialog(null, "Are you sure?","",JOptionPane.WARNING_MESSAGE);
				if(bool == 0){
					String theObject = (String) jComboBox.getSelectedItem();
					File deletionFile = Main.start.getProfile(theObject);
					deletionFile.delete();
					jComboBox.removeItem(theObject);
					pack();
					Main.start.currentlyOpenProfile = null;
				}
			}
		});
		setLayout(new BorderLayout(5, 5));
		JPanel panel1 = new JPanel(new FlowLayout());
		JPanel panel2 = new JPanel(new GridLayout(1,3,5,5));
		panel1.add(label);
		panel1.add(jComboBox);
		panel2.add(button1);
		panel2.add(button2);
		panel2.add(button3);
		add(panel1, BorderLayout.CENTER);
		add(panel2,BorderLayout.PAGE_END);
        if(Main.start.getLastOpened() != null) {
            jComboBox.setSelectedItem(Main.start.getLastOpened());
        }
		getRootPane().setDefaultButton(button1);

		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);

	}
}
