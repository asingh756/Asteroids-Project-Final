package com.mycompany.a3;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public interface ISelectable {

	boolean contains(Point p1, Point p2);
	void setSelected(boolean b);
	public boolean selected();
	public void draw(Graphics g, Point pCmpRelPrnt);

}
