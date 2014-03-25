/*
 * Copyright (c) 2014. Jasper Reddin.
 * All Rights Reserved.
 */

package tenny1028.quicktyper.command;

import tenny1028.quicktyper.gui.helpsection.HelpSection;

/**
 * Created by jasper on 1/27/14.
 */
public class HelpCommand extends Command {

	@Override
	public void execute(String[] args){
		//new HelpSection();
	}

	public String getName(){
		return "help";
	}

}
