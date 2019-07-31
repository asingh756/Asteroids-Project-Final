package com.mycompany.a3;

import java.util.Random;

public class Randomize {
	public static int getRand(int min, int max){
		Random rand = new Random();
		int r = rand.nextInt((max - min) + 1) + min;
		return r;
	} 

}
