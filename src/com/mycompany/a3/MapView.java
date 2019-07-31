package com.mycompany.a3;

import java.util.Observable;   
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;


public class MapView extends Container implements Observer{
	private IGameWorld gwProxy;
	TextArea tGameArea;
	private int px;
	private int py;
	
	public MapView(){
		
		this.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.WHITE));
		this.setLayout(new BorderLayout());
		this.getAllStyles().setBgColor(ColorUtil.BLACK);
		this.getAllStyles().setBgTransparency(255);
		
	}
	


	
	@Override
	public void update(Observable observable, Object data) {
		gwProxy = (IGameWorld) data;
		repaint();

	}
	
	public double getWidthofMap(){
		double returnnValue = (double) this.getWidth();
		return returnnValue;
	}
	
	public void paint(Graphics g){ 
		super.paint(g);
		
		Point pCmpRelPrnt = new Point(this.getX(), this.getY());
		IIterator iterator = gwProxy.getCollection().getIterator();
		
		while (iterator.hasNext()){
			GameObject curObject = iterator.getNext();
			if (curObject instanceof IDrawable)	{
				((IDrawable) curObject).draw(g, pCmpRelPrnt);
			}
		}
	}
	
	
	public void pointerPressed(int x, int y){
		px = x - getParent().getAbsoluteX();
		py = y - getParent().getAbsoluteY();
		
		Point pPtrRelPrnt = new Point(px, py);
		Point pCmpRelPrnt = new Point(getX(), getY());
		
		IIterator iterator = gwProxy.getCollection().getIterator();
		
		while (iterator.hasNext()){
			GameObject curObj = iterator.getNext();
			if (curObj instanceof ISelectable){
				ISelectable selectObj = (ISelectable)curObj;
				
				if (selectObj.contains(pPtrRelPrnt, pCmpRelPrnt)){
					selectObj.setSelected(true);
				}else{
					selectObj.setSelected(false);
				}
			}
		}
		repaint();
		System.out.println("Pressed");
	}
	



	public double getHeightOfMap(){	

		return (double) getHeight();
		
	}

}
