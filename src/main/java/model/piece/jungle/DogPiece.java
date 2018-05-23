//----------------------------------------------------------
//Green Piece 2: Dog
//----------------------------------------------------------
//standard movement
//----------------------------------------------------------

package model.piece.jungle;

import model.piece.JunglePiece;
import model.piece.Piece;

import java.awt.*;
import java.util.*;


// The model is responsible for its data and only pass them to its controller, its is implementing the Piece interface for Polymorphism design purpose
// and Low Coupling has been applied here



// Cofoja has beeen used to do Apply Design By Contract
//@Invariant("piceColumn<5 && pieceColumn > 0")

public class DogPiece extends Observable implements JunglePiece  {

	private int pieceColumn;
	private int pieceRow;
	private boolean status = true;
	ArrayList<Point> pieceMovementPosition;


//	@Requires("diceRoll != null && diceRoll < 6 && diceRoll > 0")

	public ArrayList<Point> move(int diceRoll) {
		pieceMovementPosition = new ArrayList<>();

		Point previousPoint = new Point( this.pieceRow , this.pieceColumn );

		int currentColumn = this.pieceColumn;
		int currentRow = this.pieceRow;

		int nextColumn = this.pieceColumn + diceRoll;
		int nextRow = currentRow;

		if(nextColumn > 4) {
			nextColumn = nextColumn - 4;
			nextRow++;
		}

		this.pieceColumn = nextColumn;
		this.pieceRow = nextRow;

		/*if (this.pieceColumn < 4 && diceRoll < 2) {
				this.pieceColumn += diceRoll;
			} else {
				this.pieceRow++;
				this.pieceColumn = 0;
			} */

		Point targetPosition = new Point(this.pieceRow , this.pieceColumn);

		pieceMovementPosition.add(targetPosition);
		pieceMovementPosition.add(previousPoint);

		return pieceMovementPosition;
	}




	public void submitMove(ArrayList<Point> pieceMovementPosition){

		pieceMovementPosition = pieceMovementPosition;
		setChanged();
		notifyObservers();
	}


	public void unDo(Point currentPosition, Point previousPosition){

		pieceMovementPosition = new ArrayList<>();

		this.setPieceRow(previousPosition.x);
		this.setPieceColumn(previousPosition.y);

		Point targetPosition = new Point(this.pieceRow , this.pieceColumn);

		pieceMovementPosition.add(previousPosition);
		pieceMovementPosition.add(currentPosition);

		setChanged();
		notifyObservers();


	}



	public ArrayList<Point> getPieceMovementPosition(){return pieceMovementPosition;}


	public void addObserver(Observer ob){
		super.addObserver(ob);
	}

	public void capture(Piece piecetoCapture) {
		// TODO Auto-generated method stub
		this.status = false;

	}

	public int getRow() {
		return this.pieceRow;
	}
	public void setPieceRow(int row) {this.pieceRow = row;}
    public void setPieceColumn(int column) {this.pieceColumn = column;}
	public int getColumn() {
		return this.pieceColumn;
	}

	public void setPiecePositions( Point piecePosition) {

		this.setPieceRow(piecePosition.x);
		this.setPieceColumn(piecePosition.y);
		System.out.println("The Dog is initialized in position " + piecePosition.x + " and Y is " + piecePosition.y);
	}


	public String getPieceType() {
		return "dog";
	}

	public boolean isAlive(){ return status;}


}
