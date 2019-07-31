package com.mycompany.a3;
import java.util.Observable;
import com.codename1.ui.Dialog;

public class GameWorld extends Observable implements IGameWorld{
	
	private int time, lives, score, numberOfMissiles;
	private boolean gameStop, soundSet;
	private double heightDimension, widthDimension;
	private GameCollection gObject;
    private Sound endGame, rotateLauncher, fireMissile, explosion;
    private BGSound bg;
	
	
	public GameWorld() {
		init();
	}
	public int getPlayerLives() {
		return lives;
	} 
	public void init() {
		// TODO Auto-generated method stub
		time =0;lives= 3;score =0;
		gObject = new GameCollection();
		soundSet =true;
		gameStop = false;
		explosion = new Sound("explosion.wav");fireMissile = new Sound("missileLaunch.wav");rotateLauncher = new Sound("launcherRotate.wav");endGame = new Sound("gameover.wav"); bg = new BGSound("background.wav");
		localInformer();
	}

	public void addObject(char c) {
		// TODO Auto-generated method stub
	
		switch(c)
		{
		case 'a':// adds an asteroid
			Asteroid a= new Asteroid();
			a.setRandomLocation( widthDimension,heightDimension);
			localInformer();
			gObject.add(a);
			System.out.println("Asteroid created");
			localInformer();
			break;
		case'y'://enemyship
			
			EnemyShip y =new EnemyShip();
			y.setRandomLocation(heightDimension, widthDimension);
			gObject.add(y);
			System.out.println("Enemy Ship created");
			break;
		case 'b': //create space station
			SpaceStation b = new SpaceStation();
			b.setRandomLocation(heightDimension, widthDimension);
			gObject.add(b);
			System.out.println("Space Station Created");
			break;
		case 's': //ps
			PlayerShip s = new PlayerShip();

			if(playerShip() != null) {
				System.out.println("A player ship already exists");
				
			}else {
				s.setRandomLocation(heightDimension, widthDimension);
				gObject.add(s);
				gObject.add(playerShip().getLauncher());
				System.out.println("PlayerShip Created");
			}
			localInformer();
			break;
		}
		
	}
// changes the speed of a PS
	public void speedLvl(char c) {
		// TODO Auto-generated method stub
		if(playerShip() !=null) {
			playerShip().speedChng(c);
			localInformer();
		}else
			System.out.println("error");
	}

	///turns player ship
	public void turn(char c) {
		// TODO Auto-generated method stub
		if(playerShip() !=null && c == 'r') {
			playerShip().steer(10);
			localInformer();
		}else {
			playerShip().steer(-10);
		}
		localInformer();
		
	}

	public void dir(int d) {
		// TODO Auto-generated method stub 
		if(MissileLauncher() !=null) {
			MissileLauncher().steer(d);
			localInformer();
		}
		
	}

	// Fire a missile launcher
	public void fire() {
		// TODO Auto-generated method stub
		if(MissileLauncher() !=null) {
			Missile m = playerShip().fire();
			gObject.add(m);
			localInformer();
		}
		
	}

// fires Enemy Missile	
	public void npsFire(char c) {
		// TODO Auto-generated method stub
		if(EnemyShip() != null) {
			EnemyShip().FireEnemyMissile();
			localInformer();
		}
		
	}

	public void hyperSpace() {
		// TODO Auto-generated method stub
		if(playerShip() != null) {
			playerShip().hyperSpace(heightDimension/2.0, widthDimension/2.0);
			localInformer();
		}
	}

	public void loadMissile() {
		// TODO Auto-generated method stub
		if(playerShip() !=null) {
			playerShip().reload();
			localInformer();
		}
		
	}

	// removes asteroid 
	public void kill() {
		// TODO Auto-generated method stub
		if(Asteroid() != null) {
			gObject.remove(Asteroid());
			localInformer();
		}
		
	}

	//ps eliminates the NPS
	public void eliminate() {
		// TODO Auto-generated method stub
		if(EnemyShip() != null) {
			gObject.remove(EnemyShip());
			gObject.remove(Missile());
			score = score + 10;
			localInformer();
		}
		System.out.println("Nice Shot!  10 Points!");
		

		
	}
	///missile strikes a PS
	public void explosion() {
		// TODO Auto-generated method stub
		if(playerShip() !=null) {
			gObject.remove(playerShip());
			gObject.remove(Missile());
			lives--;
			localInformer();
		}
		
		if(lives < 1) {
			gameOver();
			localInformer();
		}else {
			System.out.println("Your Ship has been destroyed ");
			addObject('s');
			localInformer();
		}
		if(lives != 0) {
			gObject.add(playerShip());
			localInformer();
		}else {
			gameOver();
			localInformer();
		}
	}
	// Asteroid impact with PS
	public void crash() {
		// TODO Auto-generated method stub
		if(Asteroid() == null) {
			System.out.println("error, no asteroid to crash into");
			localInformer();
		}else{
			System.out.println("Asteroid crashed with your PS");
			gObject.remove(playerShip());
			gObject.remove(Asteroid());
			lives-=1;
			localInformer();
			if(lives != 0) {
				addObject('s');
				localInformer();
			}else {
				gameOver();
				localInformer();
			}
		}
	}

	// ES PS CRASH
	public void hit() {
		// TODO Auto-generated method stub
		if(EnemyShip() == null || playerShip() == null) {
			System.out.println("either a PS or an NPS does not exist");
			localInformer();		
		}else{
			System.out.println("An NPS collided with your PS!");
			gObject.remove(playerShip());
			gObject.remove(EnemyShip());
			lives--;
			localInformer();
			
			if(lives != 0) {
				addObject('s');
				addObject('y');
				localInformer();
			}else {
				gameOver();
				localInformer();
			}
		}

	}
	
	
//Asteroid hits Asteroid
	public void collision() {
		// TODO Auto-generated method stub
		if(Asteroid() != null) {
			for(int i =0; i <= 1; i++) {
				gObject.remove(Asteroid());
			}
		}
		localInformer();
	}
	
	//ES hits Asteroid
	public void impact() {
		// TODO Auto-generated method stub
		if(EnemyShip() == null || Asteroid() == null) {
			System.out.println("either an asteroid or an NPS does not exist");
		}else{
			System.out.println("An Asteroid collided with an NPS");
			gObject.remove(Asteroid());
			gObject.remove(EnemyShip());
			addObject('a');
			addObject('y');
		}
		localInformer();
	}
	//missile strikes asteroid
	public void asteroidMissileImpact() {
		if(Asteroid() == null || Missile() == null) {
			System.out.println("Either an Asteroid or a Missile Does Not Exist");
		}else {
			gObject.remove(EnemyShip());
			gObject.remove(Missile());
			score = score + 10;
		}
		localInformer();
	}
	public void tickTock(double gameTick) {
		// TODO Auto-generated method stub
		time += gameTick;
		for(int i =0; i<gObject.getSize(); i++) 
		{
		GameObject obj = gObject.getObjectAtIndex(i);
		if(obj instanceof IMoveable) 
		{
		IMoveable moveObj = (IMoveable)obj;
		moveObj.Move(widthDimension,heightDimension,gameTick);

		if(gObject.getObjectAtIndex(i) instanceof Missile)
		{
		if(((Missile)gObject.getObjectAtIndex(i)).getFuel() == 0)
		{
		gObject.remove(gObject.getObjectAtIndex(i)); 
		}
		}
		else if (moveObj instanceof PlayerShip)
		{
		((PlayerShip)moveObj).MoveLauncher();
		}
		}
		else if(obj instanceof SpaceStation)
		{
		((SpaceStation) gObject.getObjectAtIndex(i)).blinkCount(); 
		}
		}
		localInformer();
	}
// display your stats 
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("Time: " + time + " ... TICK TOCK!");
		if(playerShip() != null)
			System.out.println("Missile Count: " + playerShip().getMissileNum() + " left.");
		System.out.println("YOUR SCORE: " + score + " NICE!");
		System.out.println("Lives Left " + lives + " ...There are never enough!");
		
	}
	public void endGameDisplay() {
		System.out.println("Time: " + time);
		System.out.println("YOUR SCORE: " + score + " NICE!");
		System.out.println(" Lives Left " + lives + " Better Luck Next Time.");
	}
	public void mapp() {
		// TODO Auto-generated method stub
		for(int i=0; i < gObject.getSize(); i++) {
			if(!(gObject.getObjectAtIndex(i) instanceof MissileLauncher)) {
				System.out.println(gObject.getObjectAtIndex(i));
			}
		}
		localInformer();
	}
	// GAME OVER
	public void gameOver() {
		
		 
		  System.out.println("GAME OVER");
		  endGameDisplay();
		  System.out.println(" ");
		  
		  System.out.println("BYE");
		  System.exit(0);
		  
		  localInformer();
		 }

	
	public PlayerShip playerShip() {
		for(int i=0; i < gObject.getSize(); i++) {
			if(gObject.getObjectAtIndex(i) instanceof PlayerShip) {
				return (PlayerShip) gObject.getObjectAtIndex(i);
			}
		}
		localInformer();
		System.out.println("A Player Ship does not exist");
		return null;
	}
	public MissileLauncher MissileLauncher() {
		for(int i=0; i < gObject.getSize(); i++) {
			if(gObject.getObjectAtIndex(i) instanceof MissileLauncher) {
				return (MissileLauncher) gObject.getObjectAtIndex(i);
			}
		}
		localInformer();
		return null;
	}
	public EnemyShip EnemyShip() {
		for(int i=0; i < gObject.getSize(); i++) {
			if(gObject.getObjectAtIndex(i) instanceof EnemyShip) {
				return (EnemyShip) gObject.getObjectAtIndex(i);
			}
		}
		localInformer();
		return null;
	}
	public Asteroid Asteroid() {
		for(int i=0; i < gObject.getSize(); i++) {
			if(gObject.getObjectAtIndex(i) instanceof Asteroid) {
				return (Asteroid) gObject.getObjectAtIndex(i);
			}
		}
		localInformer();
		System.out.println("An Asteroid does not exist");
		return null;
	}
	public Missile Missile() {
		for(int i=0; i < gObject.getSize(); i++) {
			if(gObject.getObjectAtIndex(i) instanceof Missile) {
				return (Missile) gObject.getObjectAtIndex(i);
			}
		}
		localInformer();
		return null;
	}
	public Missile playerMissile() {
		for(int i =0; i< gObject.getSize(); i++) {
			if(gObject.getObjectAtIndex(i) instanceof Missile) {
				if(( (Missile) gObject.getObjectAtIndex(i)).getFriendly())
					return (Missile) gObject.getObjectAtIndex(i);
			}
		}
		localInformer();
		System.out.println("A Player Missile does not exist");
		return null;
	}
	public Missile EnemyMissile() {
		for(int i=0; i<gObject.getSize(); i++) {
			if(gObject.getObjectAtIndex(i) instanceof Missile) {
				if(!((Missile) gObject.getObjectAtIndex(i)).getFriendly())
					return (Missile) gObject.getObjectAtIndex(i);
			}
		}
		localInformer();
		System.out.println("An Enemy Missile does not exist");
		return null;
	}
	
	public SpaceStation SpaceStation() {
		for(int i=0; i<gObject.getSize(); i++) {
			if(gObject.getObjectAtIndex(i) instanceof SpaceStation) {
				return(SpaceStation) gObject.getObjectAtIndex(i);
			}
		}
		localInformer();
		System.out.println("A Space Station does not exist");
		return null;
	}
	

	public void RefuelMissile() {
		IIterator iterator = gObject.getIterator();
		while(iterator.hasNext()){
			GameObject curObj = iterator.getNext();
			if(curObj instanceof Missile && ((Missile) curObj).isSelected()){
				((Missile)curObj).resetFuel();
				break;	
			}
		}
	}
	
	
	
	public boolean gameStop() {
		return gameStop;
	}
	public void setGameStop(boolean gameStop) {
		this.gameStop = gameStop;
			localInformer();
}
	
	public int getPoints() {return score;}
	
	public int getMissileCount() {return numberOfMissiles;}
	
	public int getTime() {return time;}
	
	public boolean getSoundSetting() {return soundSet;}
	
	public void setSoundSetting() {soundSet= !soundSet;}
	
	
	public void setHeightDimension(double mHeight) {heightDimension = mHeight;}
	
	public double getHeightDimension() {return heightDimension;	}
	
	public void setWidthDimension(double mWidth) {widthDimension = mWidth;}
	 
	public double getWidthDimension() {return this.widthDimension;}
	
	@Override
	public int getLives() {return lives;}
	

	public GameCollection getCollection() {return this.gObject;}
	
	
	public void setSound() {
		if(soundSet)
			soundSet = false;
		else
			soundSet=true;
		localInformer();
	}
	
	
	private void localInformer() {
		GameWorldProxy p = new GameWorldProxy(this);
		this.setChanged();
		this.notifyObservers(p);

	}
	public void playMusic() {
		bg.play();
	}
	public void pauseMusic()
	{
		bg.pause();
	}
	
}
