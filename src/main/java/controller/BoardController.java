package controller;

import design.BoardFactory;
import design.HistoryManager;
import design.MoveCommand;
import model.Player;
import model.piece.Piece;
import view.Board;
import view.MainBoard;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;

// BoardController is responsible for creating and controlling the View (which is Board here that has been created through BoardFactroy)
// and also getting the data from Model and passing them to View. MVC Design has been applied here.
// Dependency Inversion also has been applied to send the diceController and PieceController to BoardController in constructor.
// Cofoja has been used for this class to show the applied Design By Contract pattern

public class BoardController implements Serializable {
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

    HistoryManager history = new HistoryManager();


    private boolean playerTurns = true;

    private boolean playerUnDo = false;
    private int unDoCounter = 0;


	public BoardController(String boardType , int numberOfPieces, DiceController diceController , PieceController pieceController ){
		mainBoard = new MainBoard(this);
		this.diceController = diceController;
		this.pieceController = pieceController;
		//piecesMap = pieceController.getPiecesMap();

		pieceArrayList = pieceController.getPiecesMap();

		}


 	public void play(Piece piece){

		this.playerUnDo = false;
		this.unDoCounter = 0;
		playerToMove(piece.getType(), piece);
	}

	public void move(Piece piece){
		movement = diceController.rollDice();
		// create a new command
		MoveCommand moveCommand = new MoveCommand(piece, movement, this.pieceController);
		// Calling history - command design pattern
		history.addAndExecute(moveCommand);
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
		userPlayer.setTurn(true);
		board.setTurn(userPlayer.getPlayerName());
		opponentPlayer = new Player( opponentPieces.get(0).getType(), this, opponentPieces);

		// initialize the players based on the selected team of the program's user

		initializeTeamPiecesOnBoard();
		bindObservablesAndObserver(board);
	}

//	@Ensures("board != null && board instanceOf Board" )
	public Board getBoard(){
		return this.board;
	}

    public void playerToMove(String pieceTeam , Piece pieceName){

		// first Team to move

		if (userPlayer.getPlayerName().contains(pieceTeam) && playerTurns) {
	    	userPlayer.move(pieceName);
			board.setTurn(opponentPlayer.getPlayerName());
			opponentPlayer.setTurn(true);
			userPlayer.setTurn(false);
			switchPlayer();
		}

		this.checkVictory();

		// second team to move
		if(opponentPlayer.getPlayerName().contains(pieceTeam)  && !playerTurns) {
	    	opponentPlayer.move(pieceName);
			board.setTurn(userPlayer.getPlayerName());
			userPlayer.setTurn(true);
			opponentPlayer.setTurn(false);
			switchPlayer();
		}

		this.checkVictory();
	}


	// add Observer (board) to the Observable objects(pieces)
    public void bindObservablesAndObserver(Board board){
		for(Piece piece: pieceArrayList)
			piece.addObserver(board);
	}

	public void switchPlayer(){
		playerTurns = !playerTurns;
	}

	// TODO Auto-generated method stub
    public void resetBoard(){
		history = new HistoryManager();
		userPlayer.setUnDoCounter(0);
		userPlayer.setTurn(true);
		board.setTurn(userPlayer.getPlayerName());
		opponentPlayer.setUnDoCounter(0);
	}

	public void playerUnDo(){

		setTurnForUnDo();

		if (this.unDoCounter == 0) {
			if (userPlayer.getTurn() ) {
				this.userPlayer.unDo();
				System.out.println("Inside the playUnDO method for " + userPlayer.getPlayerName());
			} else  {
				this.opponentPlayer.unDo();
				System.out.println("Inside the playUnDO method for " + opponentPlayer.getPlayerName());
			}
		}
		else {
			if(userPlayer.getTurn()) {
				this.userPlayer.unDo();
				System.out.println("Inside the playUnDO method for " + userPlayer.getPlayerName());
			}
			else {
				this.opponentPlayer.unDo();
				System.out.println("Inside the playUnDO method for " + opponentPlayer.getPlayerName());
			}
		}

		this.unDoCounter++;
	}

	public void setTurnForUnDo() {


		if(!this.playerUnDo) {
			System.out.println("Inside the setTurnForUnDo method");

			if (userPlayer.getTurn()) {
				userPlayer.setTurn(false);
				opponentPlayer.setTurn(true);
				board.setTurn(opponentPlayer.getPlayerName());
				switchPlayer();
			} else {
				userPlayer.setTurn(true);
				opponentPlayer.setTurn(false);
				board.setTurn(userPlayer.getPlayerName());
				switchPlayer();
			}

			this.playerUnDo = true;
		}
 	}

 	public void checkVictory() {
		ArrayList<Piece> junglePieces = new ArrayList<Piece>();
		ArrayList<Piece> oceanPieces = new ArrayList<Piece>();

		int junglePiecesDeadCount = 0;
		int oceanPiecesDeadCount = 0;
		String winner = "";
		for(Piece piece : this.pieceArrayList) {
			if(piece.getPieceType().equals("dog") || piece.getPieceType().equals("lion") || piece.getPieceType().equals("rabbit") || piece.getPieceType().equals("turtle")) {
				if(!piece.isAlive()) {
					junglePiecesDeadCount++;
				} else {
					if(piece.getRow() == 4 && piece.getColumn() < 4) {
						winner = "jungle";
					}
				}
			} else {
				if(!piece.isAlive()) {
					oceanPiecesDeadCount++;
				} else {
					if(piece.getRow() == 0 && piece.getColumn() < 4) {
						winner = "ocean";
					}
				}
			}
		}

		if(junglePiecesDeadCount == 4) {
			winner = "ocean";
		} else if (oceanPiecesDeadCount == 4) {
			winner = "jungle";
		}

		if(winner.equals("jungle")) {
			JOptionPane.showMessageDialog(null, "Jungle player wins!");
			System.exit(0);
		} else if (winner.equals("ocean")) {
			JOptionPane.showMessageDialog(null, "Ocean player wins!");
			System.exit(0);
		}
	}

	public void unDo(){
		history.undoTheLast();
	}

	public void saveGame(){

		String path = System.getProperty("user.dir") + "/myGame.txt";
		System.out.println(path);

		try {
			FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
			ObjectOutputStream gameHistory = new ObjectOutputStream(fileOutputStream);
			gameHistory.writeObject(history);
			gameHistory.close();
			fileOutputStream.close();

		}catch(IOException io) {System.out.println("HistoryManager " + io);}


		System.exit(0);}

	public void reLoad(){
		String path = System.getProperty("user.dir") + "/myGame.txt";
		System.out.println(path + "insaide reload boardcontroller");
     try {
    	FileInputStream fi = new FileInputStream(new File(path));
    	ObjectInputStream oi = new ObjectInputStream(fi);
    	history = (HistoryManager) oi.readObject();
    	oi.close();
	    fi.close();
     }catch(Exception io) {System.out.println("Boardcontroller reload" + io);}

        System.out.println("the size of the history reloaded " + history.size() + "  " + history);
		history.reload();

	}
}
