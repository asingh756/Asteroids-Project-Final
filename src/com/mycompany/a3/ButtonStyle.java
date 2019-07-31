package com.mycompany.a3;


import com.codename1.charts.util.ColorUtil; 
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.plaf.Border;


public class ButtonStyle extends Button{
	
	public ButtonStyle(Command cmd)
	{
		
		this.getUnselectedStyle().setBgColor(ColorUtil.rgb(255,200,0));
		this.getUnselectedStyle().setFgColor(ColorUtil.BLUE); //button TEXT
		

		//------------------------------------------------------------------------------------------

		
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBorder(Border.createBevelRaised());
		this.getAllStyles().setBorder(Border.createBevelLowered());
		this.getAllStyles().setBorder(Border.createDoubleBorder(2,ColorUtil.BLACK));
		this.getAllStyles().setMargin(TOP,1);
		this.getAllStyles().setMargin(BOTTOM,1);
		
		//------------------------------------------------------------------------------------------------

		//--------------------------------------------------------------------------------------------------
		
		this.getDisabledStyle().setBgTransparency(255);
		this.getDisabledStyle().setBgColor(ColorUtil.YELLOW);
		this.getDisabledStyle().setFgColor(ColorUtil.YELLOW);
		this.getDisabledStyle().setStrikeThru(true);
		
		//-----------------------------------------------------------------------------------------------
		this.getPressedStyle().setBgTransparency(255/2);
		this.getPressedStyle().setBgColor(ColorUtil.rgb(255,200,0));
		this.getPressedStyle().setFgColor(ColorUtil.CYAN);
		
		//--------------------------------------------------------------------------------------------------
		this.setFocusable(false);
		this.setCommand(cmd);
	}
	

}
