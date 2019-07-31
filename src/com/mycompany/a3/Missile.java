package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

public class Missile extends MoveableGameObject implements ICollider, IDrawable, ISelectable {
	
	private boolean collisionFlag = false;
	private final int MISSILE_FUEL_LEVEL = 100;
	private final int SIZE_OF_MISSILE = 10;
	boolean friendly;
	private int fuel;
	private boolean selected = false;
	private int scoreEarn =0;
	
	
	public Missile( Point2D position, boolean friend, int missileDir, int speed) {
		setSpeed(speed);
		fuel =MISSILE_FUEL_LEVEL;
		setSize(SIZE_OF_MISSILE);
		setDirection(missileDir);
		setPosition(position);
		
		if(friend) {
			setColor(54,160,12);//green
			friendly = true;
		}else {
			setColor(126,12,160); //purple
			friendly = false;
		}
	}
	
	public void resetFuel() { fuel = MISSILE_FUEL_LEVEL;}
	
	public int getFuel() {return fuel;}
	
	public void ResetFuel(){fuel = MISSILE_FUEL_LEVEL;}
	
	public boolean getFriendly(){return friendly;}
	
	public String toString() {
		String superDesc = super.toString();
		String localDesc =" fuel level: " + fuel;
		String returnValue = "-----";
		if(friendly) {
			returnValue = "PSM: "+superDesc+localDesc;
		}else {
			returnValue = "ESM: " + superDesc + localDesc;
		}
		return returnValue;
	}


	public void setSelected(boolean b) {selected = b;}
	public boolean isSelected() {return selected;}
	
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		if (isSelected()){ g.setColor(ColorUtil.WHITE);
		}else{			
			g.setColor(this.getColor());
		}
		if(this.getFriendly())
			g.setColor(ColorUtil.WHITE);
		if(!this.getFriendly())
			g.setColor(ColorUtil.rgb(255, 0, 0));
		int xLoc = (int)this.getPosition().getX() + pCmpRelPrnt.getX() - (SIZE_OF_MISSILE / 2);
		int yLoc = (int)this.getPosition().getY() + pCmpRelPrnt.getY() - (SIZE_OF_MISSILE / 2);
		g.setColor(ColorUtil.WHITE);
		g.drawRect(xLoc, yLoc, SIZE_OF_MISSILE, SIZE_OF_MISSILE);
		g.fillRect(xLoc, yLoc, SIZE_OF_MISSILE, SIZE_OF_MISSILE);		
	}
	
	
	

	@Override
	public boolean contains(Point p1, Point p2) {
		int px = p1.getX();
		int py = p1.getY();
		
		int xLoc = (int)this.getPosition().getX() + p2.getX();
		int yLoc = (int)this.getPosition().getY() + p2.getY();
		
		if ( ((px >= xLoc - getSize() /  2) && (px <= xLoc + getSize() / 2)) && ((py >= yLoc - getSize() / 2) && (py <= yLoc + getSize() / 2))){
			return true;
		}
		return false;
	}

	@Override
	public boolean selected() {
		// TODO Auto-generated method stub
		return selected;
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
		double distanceFromCenter = (dx * dx + dy * dy);
		
		int thisRadius= this.getSize() / 2;
		int otherRadius= ((GameObject)other).getSize() / 2;
		int radiiSqr= (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);
		if (distanceFromCenter <= radiiSqr)
			result = true;
		
		return result;
	}

	@Override
	public void collisionHandler(ICollider o) {
		if(this.getFriendly())
		{
			if(o instanceof Asteroid){
				this.setCollisionFlag();
				o.setCollisionFlag();
				setScoreEarn(10);
			}
			else if(o instanceof EnemyShip){
				this.setCollisionFlag();
				o.setCollisionFlag();
			}
			
		}
		else if(!this.getFriendly()){
			if(o instanceof PlayerShip){
				this.setCollisionFlag();
				o.setCollisionFlag();
			}
		}
		
	}

	@Override
	public void setCollisionFlag() {
		// TODO Auto-generated method stub
		collisionFlag = true;

	}

	@Override
	public boolean getCollisionFlag() {
		// TODO Auto-generated method stub
		return collisionFlag;
	}

	public int getScoreEarn() {
		return scoreEarn;
	}

	public void setScoreEarn(int scoreEarn) {
		this.scoreEarn = scoreEarn;
	}

}
