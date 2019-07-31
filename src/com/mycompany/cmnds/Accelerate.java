package com.mycompany.cmnds;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class Accelerate extends Command {
	private GameWorld gw;
	
	public Accelerate(GameWorld gw){
		super("Accelerate");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		gw.speedLvl('i');
		System.out.println("Accelerate");
	}
}
