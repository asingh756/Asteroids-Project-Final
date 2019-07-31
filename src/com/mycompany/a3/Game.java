package com.mycompany.a3;

import com.codename1.ui.Form; 
import com.codename1.ui.Label;
import com.codename1.ui.util.UITimer;


import com.codename1.ui.CheckBox; 
import com.codename1.ui.Container;

import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.cmnds.*;

import java.lang.String;

public class Game extends Form implements Runnable{
	
	public final double gameTick = 20;
	private boolean paused = false;

	private ButtonStyle asteroid, hyperJump, turnLaunchRight, turnLaunchLeft, turnRightB, turnLeftB, speedDOWN, speedUP, 
	firePlayerMissile, addPS, addSS, refuelShip, pauseGame;
	private GameWorld gw;
	private PointsView pView;
	private MapView mView;
	private UITimer timer;
	private Toolbar menu;
	private Accelerate increaseSpeed;
	private DecreaseSpeed decreaseSpeed;
	private LeftTurn left;
	private RightTurn right;
	private LeftTurnLauncher turnLauncherL;
	private RightTurnLauncher turnLauncherR;
	private HyperSpace hyperSpace;
	private Refuel refuel;
	private PauseGame pause;
	private PlayerFire fire;
	private static String versionTitle = "Asteroid Game";
	private BGSound background;
	private int time = 0;
	private SoundCmd s;
	

	public Game() {
		
		/*gw = new GameWorld();
		mView = new MapView();
		pView = new PointsView();
		this.setLayout(new BorderLayout());
		this.setScrollable(false);
		gw.setHeightDimension(mView.getHeightOfMap());
		gw.setWidthDimension(mView.getWidthofMap());
		gw.addObserver(mView);
		gw.addObserver(pView);
		gameSetUp();
		this.addComponent(BorderLayout.NORTH, pView);
		this.addComponent(BorderLayout.CENTER, mView);
		gw.init();
		this.show();
		timer = new UITimer(this);
		timer.schedule((int)gameTick, true, this);
		background = new BGSound("background.wav");
*/
		gw = new GameWorld();
		mView = new MapView();
		pView = new PointsView();

		this.setLayout(new BorderLayout());
		this.setScrollable(false);

		gw.addObserver(mView);
		gw.addObserver(pView);

		this.addComponent(BorderLayout.NORTH, pView);
		this.addComponent(BorderLayout.CENTER, mView);

		gameSetUp();

		gw.setHeightDimension(mView.getHeightOfMap());
		gw.setWidthDimension(mView.getWidthofMap());

		gw.init();

		this.show();

		timer = new UITimer(this);
		timer.schedule((int)gameTick, true, this);
		background = new BGSound("background.wav");


	}
	
	private void gameSetUp() {menuSet(); 	setTemplates();}

	private void setTemplates() {
		
		Container main = new Container(new BoxLayout(BoxLayout.Y_AXIS));



	
		//------------------------------------------------------------------------
		// OBJECT ADDITIONS
		
		AsteroidAddition asteroidAdd = new AsteroidAddition(gw);
		asteroid = new ButtonStyle(asteroidAdd);
		main.add(asteroid);
		
		SpaceStationAddittion spaceStation = new SpaceStationAddittion(gw);
		addSS = new ButtonStyle(spaceStation);
		main.add(addSS);
 		
 		PlayerAddition playerAdd = new PlayerAddition(gw);
		addPS = new ButtonStyle(playerAdd);
		main.add(addPS);
		
		//---------------------------------------------------------------------------------------
 		
		//---------------------------------------------------------------------------
				//firing 
				
		fire = new PlayerFire(gw);
		firePlayerMissile = new ButtonStyle(fire);
		addKeyListener(-90, fire);
		main.add(firePlayerMissile);

		//---------------------------------------------------------------------------------------
		//Speed
			
 		increaseSpeed = new Accelerate(gw);
		speedUP = new ButtonStyle(increaseSpeed);
		addKeyListener('w', increaseSpeed);
		addKeyListener(-91, increaseSpeed);
		main.add(speedUP);
 		
 		decreaseSpeed = new DecreaseSpeed(gw);
		speedDOWN= new ButtonStyle(decreaseSpeed);
		addKeyListener('s', decreaseSpeed);
		addKeyListener(-92, decreaseSpeed);
		main.add(speedDOWN);
		
		
 		// Turning
 		
		
		left = new LeftTurn(gw);
		turnLeftB = new ButtonStyle(left);
		addKeyListener('a', left);
		addKeyListener(-93, left);
		main.add(turnLeftB);
 		
		right = new RightTurn(gw);
		turnRightB = new ButtonStyle(right);
		addKeyListener('d', right);
		addKeyListener(-94, right);
		main.add(turnRightB);
		
		turnLauncherL = new LeftTurnLauncher(gw);
		turnLaunchLeft = new ButtonStyle(turnLauncherL);
		addKeyListener(44, turnLauncherL);
		main.add(turnLaunchLeft);
		
		turnLauncherR = new RightTurnLauncher(gw);
		turnLaunchRight = new ButtonStyle(turnLauncherR);
		addKeyListener(46, turnLauncherR);
		main.add(turnLaunchRight);
		
		//-------------------------------------------------------------------------
		// reset to center
				
		hyperSpace = new HyperSpace(gw);
		 hyperJump = new ButtonStyle(hyperSpace);
		addKeyListener('j', hyperSpace);
		main.add(hyperJump);
		
		//----------------------------------------------------------------
		// Refuel 
		refuel = new Refuel(gw);
		refuelShip = new ButtonStyle(refuel);
		refuelShip.setEnabled(false);
		main.add(refuelShip);
		// pause
		pause = new PauseGame(this);
		pauseGame = new ButtonStyle(pause);
		this.addKeyListener('p', pause);
		main.add(pauseGame);
		//----------------------------------------------------------------
		
		mView.setWidth(this.getWidth() - main.getPreferredW() - mView.getX());
		
		this.addComponent(BorderLayout.WEST, main);
		

	}
	
	private void menuSet() {
		Toolbar gameMenu = new Toolbar();
		this.setToolbar(gameMenu);
		
		gameMenu.setTitle(versionTitle);
		
		NewGame startNewGame = new NewGame();
		gameMenu.addCommandToSideMenu(startNewGame);
		SaveGame save = new SaveGame();
		gameMenu.addCommandToSideMenu(save);
		Undo undo = new Undo();
		gameMenu.addCommandToSideMenu(undo);
		CheckBox soundOn = new CheckBox("Sound");
		SoundCmd soundCommand = new SoundCmd(gw, soundOn);
		soundOn.setCommand(soundCommand);
		gameMenu.addCommandToSideMenu(soundCommand);
		AboutGame aboutCommand = new AboutGame();
		gameMenu.addCommandToSideMenu(aboutCommand);
		ExitGame quit = new ExitGame();
		gameMenu.addCommandToSideMenu(quit);
	}

	
	
	@Override
	public void run() {
		gw.setHeightDimension(mView.getHeightOfMap());
		gw.setWidthDimension(mView.getWidthofMap());
		if(gw.getSoundSetting() == true) {
			gw.playMusic();
		}else {
			gw.pauseMusic();
		}
		gw.tickTock(gameTick);
		time += gameTick;
		
		if (time >= 5000 && time % 500 == 0){
			int roll = Randomize.getRand(0, 100);
			if (roll <= 20){
				gw.addObject('y');
			}
		}
		
		if (gw.getLives() == 0){
			background.pause();
			timer.cancel();
			gw.gameStop();
		}
		
	}
	public void PauseAndUnPauseGame(){
		this.paused = !paused;
		asteroid.setEnabled(!asteroid.isEnabled());
		addSS.setEnabled(!addSS.isEnabled());
		addPS.setEnabled(!addPS.isEnabled());
		speedUP.setEnabled(!speedUP.isEnabled());
		speedDOWN.setEnabled(!speedDOWN.isEnabled());
		turnLeftB.setEnabled(!turnLeftB.isEnabled());
		turnRightB.setEnabled(!turnRightB.isEnabled());
		turnLaunchLeft.setEnabled(!turnLaunchLeft.isEnabled());
		turnLaunchRight.setEnabled(!turnLaunchRight.isEnabled());
		firePlayerMissile.setEnabled(!firePlayerMissile.isEnabled());
		hyperJump.setEnabled(!hyperJump.isEnabled());
		refuelShip.setEnabled(!refuelShip.isEnabled());
		
		if (paused){
			timer.cancel();
			background.pause();
			pauseGame.setText("Resume");
			this.removeKeyListener('w', increaseSpeed); this.removeKeyListener(-91, increaseSpeed);
			this.removeKeyListener('s', decreaseSpeed); this.removeKeyListener(-92, decreaseSpeed);
			this.removeKeyListener('a', left);this.removeKeyListener(-93, left);
			this.removeKeyListener('d', right);this.removeKeyListener(-94, right);
			this.removeKeyListener(44, turnLauncherL);
			this.removeKeyListener(46, turnLauncherR);
			this.removeKeyListener(-90, fire);this.removeKeyListener('j', hyperSpace);

		}else{

			timer.schedule((int)gameTick, true, this);
			background.play();
			pauseGame.setText("Pause");
			this.addKeyListener('w', increaseSpeed);this.addKeyListener(-91, increaseSpeed);
			this.addKeyListener('s', decreaseSpeed);this.addKeyListener(-92, decreaseSpeed);
			this.addKeyListener('a', left);this.addKeyListener(-93, left);
			this.addKeyListener('d', right);this.addKeyListener(-94, right);
			this.addKeyListener(44, turnLauncherL);
			this.addKeyListener(46, turnLauncherR);
			this.addKeyListener(-90, fire);this.addKeyListener('j', hyperSpace);
		}
	}
	

}
