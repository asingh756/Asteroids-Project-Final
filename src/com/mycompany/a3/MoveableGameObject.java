package com.mycompany.a3;

import com.codename1.ui.geom.Point2D;

public abstract class MoveableGameObject extends GameObject implements IMoveable{
	
	private int speed, direction;
	
	public MoveableGameObject() {
		speed = rand.nextInt(16);
		direction = rand.nextInt(360);
	}
	 
	public void Move(double mWidth, double mHeight, double time) {
		// store the current position
		Point2D previousPosition = getPosition();
		//initializing the new position
		Point2D currentPosition = new Point2D(0,0); 
		
		
		// positioning the shifted coordinate plane
		int properAngle = 90-direction;
		
		
		double deltaX =0;
		double deltaY =0;
		if(direction ==0 || direction == 180) {
			deltaY = Math.sin(Math.toRadians(properAngle)) * speed;
		}
		else if(direction == 90 || direction == 270) {
			deltaX = Math.cos(Math.toRadians(properAngle)) * speed;
		}else {
			deltaX = Math.cos(Math.toRadians(properAngle))*speed; 
			deltaY = Math.sin(Math.toRadians(properAngle)) * speed;
			
		}
		
		currentPosition.setX(deltaX + previousPosition.getX());
		currentPosition.setY(deltaY+ previousPosition.getY());
		
		if (currentPosition.getX() >= mWidth){
			currentPosition.setX(currentPosition.getX() - mWidth);
		}
		else if (currentPosition.getX() <= 0.0){
			currentPosition.setX(mWidth - Math.abs(currentPosition.getX()));
		}
		
		if (currentPosition.getY() >= mHeight){
			currentPosition.setY(currentPosition.getY()- mHeight);
		}
		else if (currentPosition.getY() <= 0.0){
			currentPosition.setY(mHeight - Math.abs(currentPosition.getY()));
			
		}
		currentPosition.setX(currentPosition.getX());
		currentPosition.setY(currentPosition.getY());
		
		setPosition(currentPosition);
		
		
	}
	public void setSpeed(int x){speed += x;}
	
	public int getSpeed() {return speed;}
	
	public void setDirection(int x) {direction =x;}
	
	public int getDirection() {return direction;}
	

	public String toString() {
		String superDesc = super.toString();
		String localDesc = "| Speed = " + speed  + "| Dir: " + direction +" ";
		
		String returnValue = superDesc + localDesc;
		return returnValue;
	} 
	

}
