package com.mycompany.cmnds;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class LeftTurnLauncher extends Command 
{
	private GameWorld gw;
	
	public LeftTurnLauncher(GameWorld gw){
		super("Rotate launcher left");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		gw.dir(-15);

		System.out.println("Rotate launcher left");
	}
}
