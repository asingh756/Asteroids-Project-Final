package com.mycompany.cmnds;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class LeftTurn extends Command {
	private GameWorld gw;
	public LeftTurn(GameWorld gw){
		super("Turn left");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		gw.turn('l');
		System.out.println("Turn left");
	}
}
