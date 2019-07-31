package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class PlayerShip extends MoveableGameObject implements ISteerable, ICollider, IDrawable{
	
	private int missileNum;
	private MissileLauncher l;
	private boolean collisionFlag;
	
	public void MoveLauncher() {
		l.setPosition(this.getPosition());
	}
	
	
	public PlayerShip()
	{
		
		missileNum = 10;
		setPosition(512.0,384.0);
		setSpeed(0);
		setDirection(0);
		setColor(255,255,255); 
		l = new MissileLauncher(this.getDirection(),this.getPosition());
	}
	
	public MissileLauncher getLauncher() {return l;}
	
	public Missile fire() {
		if(missileNum == 0)
			return null;
		missileNum--;
		Missile m = new Missile(getPosition(),true,l.getDirection(),this.getSpeed()+2);
		System.out.println("Player Fire");
		return m;
	}
	
	public void launcherDirChange(int x){l.steer(x);}
	
	public int getMissileNum() {return missileNum;}
	
	public int getLaunchDir() {return l.getDirection();}
	
	/*public Missile fireMissile() {
		missileNum--;   
		Missile m = new Missile(getPosition(), true, getDirection());
		System.out.println("you fired a Missile");
		return m;
	}*/
	public void hyperSpace(double x, double y) {
		setPosition(x,y);
	}
	public void reload() {
		missileNum =10;
	}

	public void speedChng(char c) {
		switch(c)
		{
		case 'i': //increase
			if(getSpeed() <15)
				setSpeed(1);
			break; // decrease
		case 'd':
			if(getSpeed()>0)
				setSpeed(-1);
			break;
			default: 
				break;
		}
	}

	public String toString() {
		String superDesc = super.toString();
		String localDesc = "| missiles:  " + missileNum;
		String returnValue = "PS: "+superDesc +localDesc +l.toString();
		return returnValue;
		
	}

	@Override
	public void steer(int amount) {
		if(amount < 0 && getDirection() + amount < 0)
			setDirection(getDirection() + amount + 360);
		else{
			if(getDirection() + amount >= 360){
				setDirection(getDirection() + amount - 360);	
			}else
				setDirection(getDirection()+amount);
		}
		
	}
	



	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(ColorUtil.WHITE);
		int xLoc = (int)this.getPosition().getX() + pCmpRelPrnt.getX();
		int yLoc = (int)this.getPosition().getY() + pCmpRelPrnt.getY();
		int[] xPoints = { xLoc, (xLoc - 20), (xLoc + 20), xLoc };
		int[] yPoints = { (yLoc + 30), (yLoc - 30), (yLoc - 30), (yLoc + 30) };
		int nPoints = 4;
		g.drawPolygon(xPoints, yPoints, nPoints);
		g.fillPolygon(xPoints, yPoints, nPoints);
		
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
		double distBetCenter = (dx * dx + dy * dy);
		int thisRadius= this.getSize() / 2;
		int otherRadius= ((GameObject)other).getSize() / 2;
		int radiiSqr= (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);
		if (distBetCenter <= radiiSqr) { result = true ; }
		return result;
	}
	@Override
	public void collisionHandler(ICollider other) {
		if ((other instanceof Asteroid || other instanceof EnemyShip) || (other instanceof Missile && !((Missile)other).getFriendly())){
			this.setCollisionFlag();
			other.setCollisionFlag();
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
