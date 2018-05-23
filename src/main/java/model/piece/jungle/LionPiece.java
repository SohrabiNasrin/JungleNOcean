//----------------------------------------------------------
//Green Piece 3: Lion
//----------------------------------------------------------
//standard dice movement | kills everyone else, only weakness is the shark
//----------------------------------------------------------

package model.piece.jungle;

//import com.google.java.contract.Invariant;
//import com.google.java.contract.Requires;
import model.piece.JunglePiece;
import model.piece.Piece;

import java.awt.*;
import java.util.*;

// Cofoja has beeen used to do Apply Design By Contract
// @Invariant("piceColumn<5 && pieceColumn > 0")

public class LionPiece extends Observable implements JunglePiece {

	private int pieceColumn;
	private int pieceRow;
	private boolean status = true;

	private ArrayList<Point> pieceMovementPosition;




///	@Requires("diceRoll != null && diceRoll < 6 && diceRoll > 0")

	public ArrayList<Point> move(int rollDice) {

		pieceMovementPosition = new ArrayList<>();
		Point previousPoint = new Point( this.pieceRow , this.pieceColumn );

		//	for (int i = 0; i < rollDice; i++) {
			if (this.pieceColumn < 4) {
				this.pieceColumn++;
				this.pieceRow++;
			} else {
				this.pieceRow++;
				this.pieceColumn = 0;
			}
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
		return "lion";
	}

	public boolean isAlive(){ return status;}


}
