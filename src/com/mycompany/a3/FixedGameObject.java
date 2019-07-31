package com.mycompany.a3;

public class FixedGameObject extends GameObject{
	private static int objectID;
	
	public int getObjectID() {
		objectID++;
		return objectID;
		
	}

}
