package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

public class MissileLauncher extends MoveableGameObject implements ISteerable, IDrawable{
	private int lDirection;
	
	public MissileLauncher(int initDir, Point2D initLoc){
		setColor(255,255,255);
		setDirection(initDir);
		setPosition(initLoc);	
	}
	
	public int getLauncherDir() {return lDirection;}

	public void setLDirection(int d) {lDirection = d;}
	
	
	public String toString() {
		String returnValue = " | Direction of Launcher: " + lDirection;
		
		return returnValue;
	}

	@Override
	public void steer(int amount) {
		if (amount < 0 && getDirection() + amount < 0)
		{

			setDirection(getDirection() + amount + 360);
		}
		else
		{
		if (getDirection() + amount >= 360)
		{
			setDirection(getDirection() + amount - 360);
		}
		else
		{
			setDirection(getDirection() + amount); 
		}
		}
		
	}


	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		int xLocation = (int)this.getPosition().getX()+pCmpRelPrnt.getX();
		int yLocation = (int)this.getPosition().getY()+pCmpRelPrnt.getY();
		double angle  = Math.toRadians(90-this.getDirection());
		double deltaX = Math.cos(angle);
		double deltaY = Math.sin(angle);
		g.drawLine(xLocation, yLocation, (int)(xLocation + (50 * deltaX)), (int)(yLocation + (50 * deltaY)));

	}
		
	

}
