package com.mycompany.a3;

import java.util.Observable;

public class GameWorldProxy extends Observable implements IGameWorld{

	private GameWorld gwp;
	
	public GameWorldProxy(GameWorld gameWorld){gwp = gameWorld;}
	
	@Override
	public int getPoints() {return gwp.getPoints();}

	@Override
	public int getMissileCount() {return gwp.getMissileCount();}

	@Override
	public int getTime() {return gwp.getTime()/1000;}

	@Override
	public int getLives() {return gwp.getPlayerLives();}

	@Override
	public boolean getSoundSetting() {return gwp.getSoundSetting();}

	@Override
	public GameCollection getCollection() {return gwp.getCollection();}

}
