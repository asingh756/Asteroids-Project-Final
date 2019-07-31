package com.mycompany.cmnds;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class RightTurn extends Command {
	private GameWorld gw;
	
	public RightTurn(GameWorld gw){
		super("Turn right");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		gw.turn('r');
		System.out.println("Turn right");
	}
}
