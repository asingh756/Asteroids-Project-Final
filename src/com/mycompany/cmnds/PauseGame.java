package com.mycompany.cmnds;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;

public class PauseGame extends Command
{
	Game g;
	
	public PauseGame(Game g)
	{
		super("Pause");
		this.g = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		g.PauseAndUnPauseGame();
	}
}