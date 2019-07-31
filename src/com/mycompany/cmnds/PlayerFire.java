package com.mycompany.cmnds;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class PlayerFire extends Command 
{
	private GameWorld gw;
	
	public PlayerFire(GameWorld gw){
		super("PS fire");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		gw.fire();
		System.out.println("PS fire");
	}
}
