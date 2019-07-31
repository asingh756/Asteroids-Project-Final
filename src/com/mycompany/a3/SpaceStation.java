package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class SpaceStation extends FixedGameObject implements ICollider,IDrawable{
	private final int WIDTH_OF_SS = 50;
	private final int HEIGHT_OF_SS = 30;
	private final int RELOADTIME =5;
	private int blinkRate, blinkTime, SSID, reloadTimer;
	private boolean light;
	private boolean collisionFlag = false;
	private boolean reloadReady  = true;
	
	
	public SpaceStation() { 
		SSID = getObjectID();
		setColor(0,255,255);
		
		blinkRate = rand.nextInt(4)+1;
		light =true;
		blinkTime =0;
	}
	public int getBlink() {return blinkRate;}
	
	private int getSSID(){return SSID;}
	
	public void blinkCount() {
		blinkTime++;
		if(blinkTime == blinkRate) {
			light = !light;
			blinkTime =0; //resets blink rate after light changes
		}
		if(!reloadReady)
			increaseReloadTimer();
	}
	private void increaseReloadTimer() {
		reloadTimer++;
		if(reloadTimer == RELOADTIME)
			reloadReady = true;
	}

	public String toString() {
		String superDesc = super.toString();
		String localDesc = " Blink Rate = :" + blinkRate;
		String returnValue = "Station: " + superDesc + localDesc;
		return returnValue;
	}
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		g.setColor(this.getColor());
		int xLoc = (int)this.getPosition().getX() + pCmpRelPrnt.getX() - (WIDTH_OF_SS / 2);
		int yLoc = (int)this.getPosition().getY() + pCmpRelPrnt.getY() - (HEIGHT_OF_SS / 2);
		g.drawArc(xLoc, yLoc, WIDTH_OF_SS, HEIGHT_OF_SS, 0, 360);
		
		if (light && reloadReady){			
			g.fillArc(xLoc, yLoc, WIDTH_OF_SS, HEIGHT_OF_SS, 0, 360);
		}
		
	}
	@Override
	public boolean collidesWith(ICollider other) {
		boolean result = false;
		double centerX = this.getPosition().getX();
		double centerY = this.getPosition().getY();
		double oCenterX = ((GameObject)other).getPosition().getX();
		double oCenterY = ((GameObject)other).getPosition().getY();
		double dx = centerX - oCenterX;
		double dy = centerY - oCenterY;
		double distBetweenCentersSqr = (dx * dx + dy * dy);
		int thisRadius= this.getSize() / 2;
		int otherRadius= ((Asteroid)other).getSize() / 2;
		int radiiSqr= (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);
		if (distBetweenCentersSqr <= radiiSqr) { result = true ; }
		return result;
	}
	@Override
	public void collisionHandler(ICollider other) {
		if (other instanceof PlayerShip && reloadReady){
			((PlayerShip) other).reload();
			reloadReady = false;
			reloadTimer = 0;
		}		
	}
	@Override
	public void setCollisionFlag() {
		collisionFlag = true;
	}
	
	@Override
	public boolean getCollisionFlag() {
		return collisionFlag;
	}
}
