package com.mycompany.cmnds;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
public class AboutGame  extends Command{

	
	public AboutGame(){
		super("About");
	}
	public void actionPerformed(ActionEvent e)
	{
		String info = "Amrit Singh \n Asteroid Game";
		Dialog.show("About", info, "Ok", null);
	}
}
