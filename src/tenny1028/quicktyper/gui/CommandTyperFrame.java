/*
 * Copyright (c) 2014. Jasper Reddin.
 * All Rights Reserved.
 */

package tenny1028.quicktyper.gui;

import tenny1028.quicktyper.Main;
import tenny1028.quicktyper.Start;
import tenny1028.quicktyper.command.Command;
import tenny1028.quicktyper.exceptions.CommandNotFoundException;
import tenny1028.quicktyper.command.HelpCommand;
import tenny1028.quicktyper.command.SayCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by jasper on 1/27/14.
 */
public class CommandTyperFrame extends JFrame implements ActionListener, KeyListener{

	public JTextArea consoleMessages;
	public JScrollPane scrollPane;
	public JTextField input;
	public JButton enter;
	public JPanel inputPanel;

	public CommandTyperFrame(){

		// Setup GUI
		super("CommandTyperFrame " + Start.VERSION_ID);
		BorderLayout b33 = new BorderLayout(5,5);
		setLayout(b33);

		consoleMessages = new JTextArea(20,60);
		consoleMessages.setEditable(false);
		consoleMessages.setFont(new java.awt.Font("Courier", 0, 12));
		consoleMessages.setLineWrap(true);
		consoleMessages.setWrapStyleWord(true);
		scrollPane = new JScrollPane(consoleMessages);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);

		input = new JTextField(35);
		input.setFont(new java.awt.Font("Courier", 0, 12));
		inputPanel = new JPanel();
		enter = new JButton("Enter");
		enter.addActionListener(this);
		input.addActionListener(this);
		input.addKeyListener(this);
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.LINE_AXIS));
		inputPanel.add(input);
		inputPanel.add(enter);
		add(inputPanel, BorderLayout.PAGE_END);

		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		// Setup Game
		input.requestFocus();

		// Start Game
		println("Welcome to CommandTyperFrame!");
		println("This is a game where you can become a faster typer.");
		println("Press any key to continue.");

	}

	public void println(String str){
		consoleMessages.setText(consoleMessages.getText() + "\n" + str);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = input.getText();
		if (str.startsWith("/")){
			try{executeCommand(str);}catch(CommandNotFoundException ex){println("Command not found");}
			input.setText("");
			input.requestFocus();
			return;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public static void executeCommand(String str, String[] args)throws CommandNotFoundException {
		Command cmd = new Command();
		String strToLowerCase = str.toLowerCase();

		if(strToLowerCase.startsWith("/help")){
			cmd = new HelpCommand();
		}if(strToLowerCase.startsWith("/say")){
			cmd = new SayCommand();
		}
		else{
			throw new CommandNotFoundException();
		}
		cmd.execute(args);
	}

	public static void executeCommand(String str)throws CommandNotFoundException{
		String[] args = str.split(" ");
		java.util.List<String> argsArrayList= new ArrayList<String>();

		// Find command args, skip first element in array because that is the actual command, not an arg
		for(String stragain:args){
			argsArrayList.add(stragain);
		}
		argsArrayList.remove(0);
		args = argsArrayList.toArray(args);


		executeCommand(str,args);
	}

}
