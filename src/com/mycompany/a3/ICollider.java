package com.mycompany.a3;

public interface ICollider 
{

	public boolean collidesWith(ICollider other);
	public void collisionHandler(ICollider o);
	public void setCollisionFlag();
	public boolean getCollisionFlag();
}
