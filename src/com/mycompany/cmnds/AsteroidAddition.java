package com.mycompany.cmnds;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AsteroidAddition extends Command{
	private GameWorld gw;
	
	public AsteroidAddition(GameWorld gw){
		super("Add asteroid");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		gw.addObject('a');
		System.out.println("Add asteroid");
	}
}
