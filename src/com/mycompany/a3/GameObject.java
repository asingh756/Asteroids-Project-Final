package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import java.util.Random;

public class GameObject {
	
	public Random rand = new Random();
	private Point2D position;
	private int rgb, size;
	 
	public GameObject() {position = new Point2D(0.0, 0.0);}
	
	public void setPosition(double x, double y) {

		position.setX(x);
		position.setY(y);
		
	}
	
	public void setPosition(Point2D newPos) {
		position.setX(newPos.getX());
		position.setY(newPos.getY());
	}
	
	public double getPositionX() {return position.getX();}
	public double getPositionY() {return position.getY();}
	
	public Point2D getPosition() {return position;}
	 

	public void setRandomLocation(double maxX, double maxY){
		
		double x = Math.round(((maxX + 1.0) * rand.nextDouble() * 10.0)) / 10.0;
		double y = Math.round(((maxY + 1.0) * rand.nextDouble() * 10.0)) / 10.0;
		if (x > maxX){
			x = maxX; 
		}
		if (y > maxY){
			y = maxY; 
		}
		position = new Point2D(x, y);
	}

	
	public void setColor(int R, int G, int B) {rgb = ColorUtil.rgb ( R,G,B);}
	
	public int getColor() {return rgb;}

	public void setSize(int size){this.size = size;}
	
	public int getSize(){return size;}
	
	public double GetLocationX()
	{
		return position.getX();
	}

	public String toString() {
		String returnValue =
		"Position: " + position.getX() + "," + position.getY()
		+ "| Color = [" + ColorUtil.red(rgb) + ", " + ColorUtil.green(rgb) +"," + ColorUtil.blue(rgb) + "]";
		return returnValue;
	}
	
	
	

}
