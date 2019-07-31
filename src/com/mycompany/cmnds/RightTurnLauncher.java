package com.mycompany.cmnds;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class RightTurnLauncher extends Command {
	private GameWorld gw;
	
	public RightTurnLauncher(GameWorld gw){
		super("Rotate launcher right");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		gw.dir(15);
		System.out.println("Rotate launcher right");
	}
}
