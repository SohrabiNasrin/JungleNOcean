//----------------------------------------------------------
//Blue Piece 1 : Dolphin
//----------------------------------------------------------
//Only moves 1 and gives the rest to shark
//----------------------------------------------------------

package model.piece.ocean;

//import com.google.java.contract.Invariant;
//import com.google.java.contract.Requires;
import model.piece.OceanPiece;
import model.piece.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


// Cofoja has beeen used to do Apply Design By Contract
//@Invariant("piceColumn<5 && pieceColumn > 0")

public class DolphinPiece implements OceanPiece {

	private int pieceColumn;
	private int pieceRow;
	private boolean status = true ;
	SharkPiece sharkPieceLink = null;

	// store the piece movement in order to do the undo later
	private Map<Integer, Point > piecePositions = new HashMap<Integer, Point>();

	// the number of moves that piece does, start with 0
	private static int theMovementNumber = 0;


	//@Requires("diceRoll != null && diceRoll < 6 && diceRoll > 0")
	public void move(int diceRoll) {

		//this.sharkPieceLink.setMovementFromDolphin(toGiveToShark);
		--diceRoll;
		if(diceRoll < 2 && this.pieceColumn < 4 ){

			this.pieceColumn += diceRoll;

		 }else {
			this.pieceRow--;
			this.pieceColumn = 0;
		}

		Point point = new Point(pieceRow , pieceColumn);
		theMovementNumber++;
		piecePositions.put(theMovementNumber , point);

	}

	public Point rollBack(){

		piecePositions.remove(theMovementNumber);
		theMovementNumber -=1;

		Point rolledbackPoint = new Point(piecePositions.get(theMovementNumber).x, piecePositions.get(theMovementNumber).y);
		return  rolledbackPoint;
	}


	public void capture(Piece piecetoCapture) {
		// TODO Auto-generated method stub

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

	public void setPiecePositions(int movementNumber , Point piecePosition) {
		piecePositions.put(movementNumber , piecePosition );
		System.out.println("The Dolphin is initialized in position " + piecePosition.x + " and Y is " + piecePosition.y);

	}


	public String getPieceType() {
		return "dolphin";
	}

	public Point getLastPosition(){ return piecePositions.get((theMovementNumber) - 1) ;}

	public boolean isAlive(){ return status;}


}
