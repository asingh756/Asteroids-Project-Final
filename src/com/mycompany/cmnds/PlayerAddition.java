package com.mycompany.cmnds;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class PlayerAddition extends Command {
	private GameWorld gw;	
	public PlayerAddition(GameWorld gw){
		super("Add PS");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		gw.addObject('s');
		System.out.println("Add PS");
	}
}
