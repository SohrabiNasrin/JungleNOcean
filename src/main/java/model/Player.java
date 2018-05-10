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

public class Player {
	//dependency on Piece
	
	private String playerName;

	private ArrayList<Piece> teamPieces;

	private String pieceToMoveName;

	private BoardController boardController;

	public Player (String name, BoardController boardController , ArrayList<Piece> teamPieces){
		this.playerName = name;
		this.boardController = boardController;
		this.teamPieces = teamPieces;
 	}
	

	public void move(Piece pieceToMoveName) {
	    	boardController.move(pieceToMoveName);
	 }

	 public String getPlayerName() {return playerName;}


}