package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import controller.BoardController;
import model.piece.Piece;
import model.piece.jungle.DogPiece;
import model.piece.jungle.LionPiece;
import model.piece.jungle.RabbitPiece;
import model.piece.jungle.TurtlePiece;
import model.piece.ocean.DolphinPiece;
import model.piece.ocean.ElectricEelPiece;
import model.piece.ocean.JellyfishPiece;
import model.piece.ocean.SharkPiece;
import view.SquareBoard;

import javax.swing.*;

public class Player {
	//dependency on Piece
	
	private String playerName;

	private ArrayList<Piece> teamPieces;
	private boolean turn = false;

	private String pieceToMoveName;
    private int unDoCounter = 0;
	private BoardController boardController;

	public Player (String name, BoardController boardController , ArrayList<Piece> teamPieces){
		this.playerName = name;
		this.boardController = boardController;
		this.teamPieces = teamPieces;
 	}
	

	public void move(Piece pieceToMoveName) {
	    	boardController.move(pieceToMoveName);
	 }

	 public void unDo(){
		if (this.unDoCounter < 3) {
			boardController.unDo();
			this.unDoCounter++;
			System.out.println("The unDoCounter is " + this.unDoCounter);
		}
		else{
			String message = this.getPlayerName() + " Team, Sorry, You have exceeded the limit for undo!";
			JOptionPane.showMessageDialog(null, message);
		}
	 }
	 public String getPlayerName() {return playerName;}
	 public int getUnDoCounter(){return this.unDoCounter;}

	 public void setTurn(boolean turn) {this.turn = turn;}
	 public boolean getTurn() {return turn;}
	 public void setUnDoCounter(int counter) {this.unDoCounter = 0 ;}


}