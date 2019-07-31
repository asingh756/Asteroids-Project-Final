package com.mycompany.a3;

public interface IIterator {

	public void remove(GameObject gameObj);
	public void remove();
	public GameObject getNext();
	public boolean hasNext();
	
}
