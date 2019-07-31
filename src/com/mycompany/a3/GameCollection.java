package com.mycompany.a3;

import java.util.Vector;



public class GameCollection implements ICollection{

	private Vector<GameObject> gObject;

	public GameCollection(){	gObject = new Vector<GameObject>();}
	
	@Override
	public IIterator getIterator() {return new IterataethroughObjects();}

	@Override
	public int getSize() { return gObject.size();}

	@Override
	public void add(GameObject objOfGame) { gObject.add(objOfGame);}

	public void remove(GameObject removeOBJECT) { gObject.remove(removeOBJECT);}
	
	public GameObject getObjectAtIndex(int objectIndex){return gObject.get(objectIndex);}
	
	private class IterataethroughObjects implements IIterator{
		private int x;
		public IterataethroughObjects(){x = -1;}

		@Override
		public void remove(GameObject gameObj) {
			gObject.remove(gameObj);
			x--;
		}
		
		@Override
		public boolean hasNext() {
			if(gObject.size() <= 0) {return false;}
			if(x == gObject.size() - 1){
				x = -1;
				return false;
			}
			return true;
		}
		@Override
		public GameObject getNext() {
			x++;
			return gObject.get(x);
		}

		@Override
		public void remove() {
			gObject.remove(x);
		}

	
	}

}
