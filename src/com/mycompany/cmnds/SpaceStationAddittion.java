package com.mycompany.cmnds;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class SpaceStationAddittion extends Command {
	private GameWorld gw;
	
	public SpaceStationAddittion(GameWorld gw){
		super("Add SS");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		gw.addObject('b');
		System.out.println("Add SS");
	}
}
