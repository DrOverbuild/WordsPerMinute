/*
 * Copyright (c) 2014. Jasper Reddin.
 * All Rights Reserved.
 */

package tenny1028.quicktyper.gui.helpsection;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jasper on 3/24/14.
 */
public class ReadUpOnStuff extends JFrame {

	HelpSection hs;

	public ReadUpOnStuff(HelpSection hs, String title, String text){
		super(title);
		this.hs = hs;
		this.hs.setVisible(false);

		JTextArea area = new JTextArea(text,20,40);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setEditable(false);
		area.setCursor(new Cursor(Cursor.TEXT_CURSOR));

		add(new JScrollPane(area), BorderLayout.CENTER);

		pack();
		setMinimumSize(getSize());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	@Override
	public void dispose(){
		super.dispose();
		hs.setVisible(true);
	}

}
