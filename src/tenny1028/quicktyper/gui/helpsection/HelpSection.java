/*
 * Copyright (c) 2014. Jasper Reddin.
 * All Rights Reserved.
 */

package tenny1028.quicktyper.gui.helpsection;

import tenny1028.quicktyper.Main;
import tenny1028.quicktyper.Profile;
import tenny1028.quicktyper.gui.ProfileViewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jasper on 1/28/14.
 */
public class HelpSection extends JFrame{

	Profile profile;

	public HelpSection(Profile profile){
		super("WordsPerMinute Help");
		setLayout(new GridLayout(4,1));
		this.profile = profile;

		add(newButton("What is Free Type?", "whatisfreetype.txt"));
		add(newButton("What is Accuracy Type?", "whatisaccuracytype.txt"));


		pack();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setMinimumSize(getSize());
	}

	public static void firstTimeLaunching(){
		JOptionPane.showMessageDialog(null,
				"Welcome to Words Per Minute. This is a typing program to help you become a faster typer.",
				"Weclome",
				JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null,
				"To start, create a new profile.",
				"Weclome",
				JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null,
				"This profile will be used to store your statistics about your typing.",
				"Weclome",
				JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void dispose(){
		super.dispose();
	}

	public HelpSection getThis(){
		return this;
	}

	public JButton newButton(final String text, final String filename){

		JButton button = new JButton(text);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ReadUpOnStuff(getThis(), text, Main.getTXTFileInJar("/helpsection/" + filename));
			}
		});

		return button;
	}

}
