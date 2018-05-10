package controller;

import design.BoardFactory;
import model.Player;
import model.piece.Piece;
import view.Board;
import view.MainBoard;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

// BoardController is responsible for creating and controlling the View (which is Board here that has been created through BoardFactroy)
// and also getting the data from Model and passing them to View. MVC Design has been applied here.
// Dependency Inversion also has been applied to send the diceController and PieceController to BoardController in constructor.


// Cofoja has been used for this class to show the applied Design By Contract pattern

public class BoardController {
	
	private MainBoard mainBoard;
	private Board board;
	private BoardFactory boardFactory ;
	private String boardType;
	private int numberOfPieces,
	            movement;
	private Player userPlayer, opponentPlayer;
	private DiceController diceController;
	private PieceController pieceController;


	private ArrayList<Piece> pieceArrayList = new ArrayList<>();
    private Map<String , Integer> pieceMovement;


    private boolean playerTurns = true;


	public BoardController(String boardType , int numberOfPieces, DiceController diceController , PieceController pieceController ){
		mainBoard = new MainBoard(this);
		this.diceController = diceController;
		this.pieceController = pieceController;
		//piecesMap = pieceController.getPiecesMap();

		pieceArrayList = pieceController.getPiecesMap();;
		}



 	public void play(Piece piece){
		playerToMove(piece.getType(), piece);
	}

	public void move(Piece piece){

		movement = diceController.rollDice();

      	ArrayList<Point> pieceMomevent = pieceController.move(movement, piece);
		System.out.println( piece + " old postions is " + "row : " + pieceMomevent.get(1).x
				+ " col " + pieceMomevent.get(1).y);


  	// update the board buttons
		board.updateThePiecePosition(piece , pieceMomevent.get(0).x, pieceMomevent.get(0).y ,
				pieceMomevent.get(1).x, pieceMomevent.get(1).y);

	}

	// initializing dataStructure in View based on Data that has been provided to controller form Model, so that view (here the board) can update itself
	public void initializeTeamPiecesOnBoard(){
		    board.initializeTeamPieces(pieceArrayList);
	}

	/**
	 *  set the pieces inital positions in their corresponding model through their controllers
	 */
	public void setPiecesInitialPositions( Piece piece, Point initialPiecePositions){
		  pieceController.setInitialPiecePositions(piece, initialPiecePositions);

	}

	public ArrayList<Piece> getPiecesMap(){
		return pieceArrayList;
	}


   // @Ensures("board != null && board instanceOf Board" )
	public void createGameBoard(String boardType , String userTeam){

		this.boardType = boardType;
		this.numberOfPieces = 4;

		// Create board via BoardFactory
		board = new BoardFactory().createBoard(boardType, numberOfPieces);

		// Binding view, which is Board here, to Controller
		board.addListener(this);

		ArrayList<Piece> usersPieces = new ArrayList<>();
		ArrayList<Piece> opponentPieces = new ArrayList<>();

		for (Piece piece : pieceArrayList) {
			if (piece.getType().contains(userTeam))
				usersPieces.add(piece);
			else opponentPieces.add(piece);
		}
		userPlayer  = new Player(userTeam ,this , usersPieces);

		opponentPlayer = new Player( opponentPieces.get(0).getType(), this, opponentPieces);
		// initialize the players based on the selected team of the program's user

		initializeTeamPiecesOnBoard();

	}

//	@Ensures("board != null && board instanceOf Board" )
	public Board getBoard(){
		return this.board;
	}

    public void playerToMove(String pieceTeam , Piece pieceName){

		// first Team to move

		if (userPlayer.getPlayerName().contains(pieceTeam) && playerTurns)
		  {
	    	userPlayer.move(pieceName);
			board.setTurn(opponentPlayer.getPlayerName());
			switchPlayer();
		  }

		// second team to move
		if(opponentPlayer.getPlayerName().contains(pieceTeam)  && !playerTurns) {
	    	opponentPlayer.move(pieceName);
			board.setTurn(userPlayer.getPlayerName());
			switchPlayer();
		}
	}


	public void switchPlayer(){
		playerTurns = !playerTurns;
	}

	// TODO Auto-generated method stub
    public void resetBoard(){

	}





	
}
