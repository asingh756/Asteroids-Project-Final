package com.mycompany.cmnds;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class Refuel extends Command{

	private GameWorld gw;
	
	public Refuel(GameWorld gw)
	{
		super("Refuel");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		gw.RefuelMissile();
	}
}
