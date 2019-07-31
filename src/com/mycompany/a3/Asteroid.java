package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Asteroid extends MoveableGameObject implements ICollider, IDrawable, ISelectable{
	
	private boolean collisionFlag = false;
	private boolean selected = false;
	
	public Asteroid() {
		setColor(173,224,62);//lime green
		setSize(rand.nextInt(10) + 40);

	}

	
	public String toString(){
		String superDesc = super.toString();
		String localDesc = "size = " + getSize();
		String returnValue = "Asteroid: " + superDesc + localDesc;
		
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
	public void draw(Graphics g, Point pCmpRelPrnt) {
		if (selected()){
			g.setColor(ColorUtil.GREEN);
		}else{
			g.setColor(this.getColor());
		}
		
		int xLoc = (int)this.getPosition().getX() + pCmpRelPrnt.getX() - (getSize() / 2);
		int yLoc = (int)this.getPosition().getY() + pCmpRelPrnt.getY() - (getSize() / 2);
		
		g.drawArc(xLoc, yLoc, this.getSize(), this.getSize(), 0, 360);
		g.fillArc(xLoc, yLoc, this.getSize(), this.getSize(), 0, 360);
		
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
		int otherRadius= ((Asteroid)other).getSize() / 2;
		
		int radiiSqr= (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);
		
		if (distBetweenCentersSqr <= radiiSqr) { result = true ; }
		
		return result;
	}
	
	@Override
	public void setSelected(boolean b) {selected = b;}
	
	@Override
	public boolean selected() {	return selected;}
	
	
	
	@Override
	public void collisionHandler(ICollider other) {
		// TODO Auto-generated method stub
		if (!(other instanceof SpaceStation)){
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
