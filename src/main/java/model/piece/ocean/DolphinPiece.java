//----------------------------------------------------------
//Blue Piece 1 : Dolphin
//----------------------------------------------------------
//Only moves 1 and gives the rest to shark
//----------------------------------------------------------

package model.piece.ocean;

import model.piece.OceanPiece;
import model.piece.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

// Cofoja has beeen used to do Apply Design By Contract
//@Invariant("piceColumn<5 && pieceColumn > 0")

public class DolphinPiece extends Observable implements OceanPiece {

	private int pieceColumn;
	private int pieceRow;
	private boolean status = true ;
	SharkPiece sharkPieceLink = null;

	private ArrayList<Point> pieceMovementPosition;


	//@Requires("diceRoll != null && diceRoll < 6 && diceRoll > 0")
	public ArrayList<Point> move(int diceRoll) {

		pieceMovementPosition = new ArrayList<>();
		Point previousPoint = new Point( this.pieceRow , this.pieceColumn );

		//this.sharkPieceLink.setMovementFromDolphin(toGiveToShark);
		--diceRoll;
		if(diceRoll < 2 && this.pieceColumn < 4 ){

			this.pieceColumn += diceRoll;

		 }else {
			this.pieceRow--;
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

	public void setSharkLink(SharkPiece shark) {
		this.sharkPieceLink = shark;
	}
	
	public int getRow() {
		return this.pieceRow;
	}
	public void setPieceRow(int row) {this.pieceRow = row;}
	public void setPieceColumn(int column) {this.pieceColumn = column;}
	public int getColumn() {
		return this.pieceColumn;
	}


	public void setPiecePositions(Point piecePosition) {
		this.setPieceRow(piecePosition.x);
		this.setPieceColumn(piecePosition.y);
		System.out.println("The Dolphin is initialized in position " + piecePosition.x + " and Y is " + piecePosition.y);
	}


	public String getPieceType() {
		return "dolphin";
	}


	public boolean isAlive(){ return status;}


}
