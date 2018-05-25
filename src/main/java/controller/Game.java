package controller;

import java.util.ArrayList;

import model.Player;

public class Game {
	
	private ArrayList<Player> playerArray = new ArrayList<Player>();
	
	public Game(Player playerOne, Player playerTwo) {
		playerArray.add(playerOne);
		playerArray.add(playerTwo);
	}
	
	private boolean isFinished() {
		//check if anyone has won
		return false;
	}
	
	/*public Player start() {
		int counter = 0;
		
		while(!isFinished()) {
			//ask player which piece they want to move (from view)
			
			//takeTurn() function requires the name of the piece the player wants to move e.g. "lion"
			playerArray.get(counter++ % 2).takeTurn(null);
		}
		
		//return the Player that wins the game
		return null;
	} */
}
