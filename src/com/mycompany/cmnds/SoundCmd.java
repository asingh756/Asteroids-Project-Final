package com.mycompany.cmnds;

//import com.codename1.ui.CheckBox;
import com.mycompany.a3.BGSound;
import com.mycompany.a3.Game;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class SoundCmd extends Command 
{
	private CheckBox cb;
	private GameWorld gw;

	public SoundCmd(GameWorld gw, CheckBox cb){
		super("Sound ON/OFF");
		this.gw = gw;
		this.cb = cb;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		if (cb.isSelected()){
			System.out.println("SOUND ON");
			gw.playMusic();
		}else{
			System.out.println("SOUND OFF");
			gw.pauseMusic();
		}
		gw.setSoundSetting();
	}
}
