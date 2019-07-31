package com.mycompany.cmnds;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class DecreaseSpeed extends Command {
	private GameWorld gw;
	
	
	public DecreaseSpeed(GameWorld gw){
		super("Decelerate");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		gw.speedLvl('d');
		System.out.println("Decelerate");
	}
}
