package com.mycompany.a3;
import java.util.Observable; 
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;


public class PointsView extends Container implements Observer{

	private Label points, numberOfMissiles, time, sound, remainingLives ;
	
	public PointsView() {
		this.setLayout(new FlowLayout(LEFT));
		gameSetUp();
	}
	 
	public void gameSetUp(){
		pointsSet(); missileCountSet(); timeSet(); soundSet(); livesSet();
	}
	
	@Override
	public void update(Observable observable, Object data) {
		IGameWorld gameProxy = (IGameWorld) data;
		points.setText(""+Integer.toString(gameProxy.getPoints()));
		numberOfMissiles.setText(""+Integer.toString(gameProxy.getMissileCount()));
		time.setText(""+Integer.toString(gameProxy.getTime()));
		remainingLives.setText(""+Integer.toString(gameProxy.getLives()));
		
		if(gameProxy.getSoundSetting()){
			sound.setText("ON");
		}else{
			sound.setText("OFF");
		}
		this.repaint();
	}
	


	private void soundSet() {
		Label soundLabel = new Label("Sound:");
		soundLabel.getAllStyles().setFgColor(ColorUtil.rgb(255, 0, 255));
		sound = new Label("OFF");
		sound.getAllStyles().setPadding(RIGHT, 5);
		this.add(soundLabel);
		this.add(sound);
	}

	private void timeSet() {
		Label timeLabel = new Label("Elapsed Time:");
		timeLabel.getAllStyles().setFgColor(ColorUtil.rgb(255, 0, 255));
		time = new Label("0");
		time.getAllStyles().setPadding(RIGHT, 5);
		this.add(timeLabel);
		this.add(time);
	}
	private void livesSet() {
		Label livesLabel = new Label("Lives:");
		livesLabel.getAllStyles().setFgColor(ColorUtil.rgb(255, 0, 255));
		remainingLives = new Label("0");
		remainingLives.getAllStyles().setPadding(RIGHT, 5);
		this.add(livesLabel);
		this.add(remainingLives);
	}
	
	private void pointsSet() {
		Label pointsLabel = new Label("Points:");
		pointsLabel.getAllStyles().setFgColor(ColorUtil.rgb(255, 0, 255));
		points = new Label("0");
		points.getAllStyles().setPadding(RIGHT, 5);
		Container pointContainer = new Container();
		pointContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		pointContainer.add(pointsLabel);
		pointContainer.add(points);
		this.add(pointContainer);
	}
	
	private void missileCountSet() {
		Label missileLabel = new Label("Missile Count:");
		missileLabel.getAllStyles().setPadding(RIGHT, 5);
		this.add(missileLabel);
		numberOfMissiles = new Label("0");
		numberOfMissiles.getAllStyles().setPadding(RIGHT, 5);
		this.add(numberOfMissiles);
	}



}
