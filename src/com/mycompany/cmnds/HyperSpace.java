package com.mycompany.cmnds;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class HyperSpace extends Command 
{
	private GameWorld gw;
	
	public HyperSpace(GameWorld gw){
		super("Hyperspace jump");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		gw.hyperSpace();
		System.out.println("Hyperspace jump");
	}
}
