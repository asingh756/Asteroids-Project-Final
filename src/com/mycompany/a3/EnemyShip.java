package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class EnemyShip extends MoveableGameObject implements ICollider, IDrawable, ISelectable{
	private int size, missileNum;
	private boolean collisionFlag = false;
	private boolean selected = false;
	private MissileLauncher l;
	
	public EnemyShip() {
		size = (rand.nextInt(2)+1)*10;
		missileNum =2;
		setColor(255,0,0);//red
	}
	public void MoveLauncher() {
		l.setPosition(this.getPosition());
	}
	
	public int getSize() {return size;}
	
	public void setMissileNum(int n) {missileNum = n;}
	
	public int getMissileNum() {
		return missileNum;
	}
	public void FireEnemyMissile() {
		missileNum--;
		System.out.println("Enemy Fire");

	}
	public String toString() {
		String superDesc = super.toString();
		String localDesc = " Size: " + size;
		String returnValue = "NPS: "+ superDesc + localDesc;
		return returnValue;
	}

	@Override
	public boolean contains(Point p1, Point p2) {
		int xPoint = p1.getX();
		int yPoint = p1.getY();
		int xPosition = (int)this.getPosition().getX() + p2.getX();
		int yPosition = (int)this.getPosition().getY() + p2.getY();
		
		if ( ((xPoint >= xPosition - getSize() /  2) && (xPoint <= xPosition + getSize() / 2)) && ((yPoint >= yPosition - getSize() / 2) && (yPoint <= yPosition + getSize() / 2))){
			return true;
		}
		return false;
	}

	@Override
	public void setSelected(boolean b) {}

	@Override
	public boolean selected() {return false;}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		if (selected())	{
			g.setColor(ColorUtil.GREEN);
		}
		else{
			g.setColor(ColorUtil.YELLOW);
		}
		
		int xLoc = (int)this.getPosition().getX() + pCmpRelPrnt.getX();
		int yLoc = (int)this.getPosition().getY() + pCmpRelPrnt.getY();
		int[] xArr = { xLoc, (xLoc - getSize()), (xLoc + getSize()), xLoc };
		int[] yArr = { (yLoc + getSize()), (yLoc - getSize()), (yLoc - getSize()), (yLoc + getSize()) };
		int nPoints = 4;
		g.drawPolygon(xArr, yArr, nPoints);
		g.fillPolygon(xArr, yArr, nPoints);		
		
	}

	@Override
	public boolean collidesWith(ICollider other) {
		boolean result = false;
		double thisCenterX = this.getPosition().getX();
		double thisCenterY = this.getPosition().getY();
		double otherCenterX = ((GameObject)other).getPosition().getX();
		double otherCenterY = ((GameObject)other).getPosition().getY();
		double dx = thisCenterX - otherCenterX;
		double dy = thisCenterY - otherCenterY;
		double distBetweenCentersSqr = (dx * dx + dy * dy);
		int thisRadius= this.getSize() / 2;
		int otherRadius= ((GameObject)other).getSize() / 2;
		int radiiSqr= (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);
		if (distBetweenCentersSqr <= radiiSqr) { result = true ; }
		return result;
	}

	@Override
	public void collisionHandler(ICollider other) {
		if (other instanceof Asteroid || other instanceof PlayerShip || (other instanceof Missile && !((Missile)other).getFriendly()));{
			this.setCollisionFlag();
			other.setCollisionFlag();
		}
	}
	@Override
	public void setCollisionFlag() {collisionFlag = true;}

	@Override
	public boolean getCollisionFlag() {return collisionFlag;}
	
	


		
	

}
