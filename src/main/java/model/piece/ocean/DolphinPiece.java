//----------------------------------------------------------
//Blue Piece 1 : Dolphin
//----------------------------------------------------------
// If it rolls more than a 3, it moves 4
// If it rolls less than a 3, it moves 3
//----------------------------------------------------------

package model.piece.ocean;

import model.piece.OceanPiece;
import model.piece.Piece;

import javax.swing.*;
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

		String moveType = this.getMoveType();
		if(moveType.equals("Move Only 1")) {
			System.out.println(">>" + moveType + "<<");
			if(this.pieceColumn < 4) {
				this.pieceColumn++;
			} else {
				this.pieceRow++;
				this.pieceColumn = 0;
			}
		} else {
			if(diceRoll > 3) {
				diceRoll = 4;
			} else {
				diceRoll = 3;
			}

			for(int i = 0; i < diceRoll; i++) {
				if(this.pieceColumn < 4) {
					this.pieceColumn++;
				} else {
					this.pieceRow--;
					this.pieceColumn = 0;
				}
			}
		}

		if(pieceRow < 0) {
			this.pieceRow = 0;
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

	public void release(Point piecePosition){
		pieceMovementPosition = new ArrayList<>();

		this.setPieceRow(piecePosition.x);
		this.setPieceColumn(piecePosition.y);

		Point targetPosition = new Point(this.pieceRow , this.pieceColumn);

		pieceMovementPosition.add(targetPosition);
		pieceMovementPosition.add(targetPosition);

		setChanged();
		notifyObservers();

		this.status = true;
	}

	public String getMoveType() {
		JFrame frame = new JFrame("Input Dialog Example 3");
		String[] options = {"Move Normal", "Move Only 1"};
		String moveType = (String) JOptionPane.showInputDialog(frame,
				"Move Normally or Move Up 1?",
				"Choose your move:",
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				"Move");

		return moveType;
	}

}
