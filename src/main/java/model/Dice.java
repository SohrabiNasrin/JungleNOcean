package model;

import java.util.Random;

// Dice class follow Singletone Design Pattern

public class Dice {
	
	int currentValue = 0;

	private static Dice diceSingletone;

	private Dice() {
		this.currentValue = 1;
	}

	public static Dice getSingletone(){

		if (diceSingletone != null){
			return diceSingletone;
		}
		else {
			return diceSingletone = new Dice();
		}

	}

	public int roll() {
		Random random = new Random();
		this.currentValue = random.nextInt(5) + 1;
		System.out.println("Dice : " + currentValue);
		return this. currentValue;
	}
}