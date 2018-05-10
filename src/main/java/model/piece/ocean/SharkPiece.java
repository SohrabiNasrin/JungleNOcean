//----------------------------------------------------------
//Blue Piece 2 : Shark
//----------------------------------------------------------
//Gets movement from dolphin and thats added to whatever it rolls on the dice.
//It can kill the lion only, and can be killed by the lion only.
//----------------------------------------------------------

package model.piece.ocean;

//import model.DiceSingleton;
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


public class SharkPiece implements OceanPiece {
	
	private int movementFromDolphin = 1;
	private int pieceColumn;
	private int pieceRow;
	private boolean status = true;

	// store the piece movement in order to do the undo later
	private Map<Integer, Point > piecePositions = new HashMap<Integer, Point>();

	// the number of moves that piece does, start with 0
	private static int theMovementNumber = 0;

	//@Requires("diceRoll != null && diceRoll < 6 && diceRoll > 0")
	public void move(int diceRoll) {

			if(this.pieceColumn < 4) {
				this.pieceColumn++ ;
			 }else if(pieceRow>2){
				this.pieceRow-- ;
				this.pieceColumn = 0 ;
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
	
	public void setMovementFromDolphin(int movement) {
		this.movementFromDolphin = movement;
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
		System.out.println("The Shark is initialized in position " + piecePosition.x + " and Y is " + piecePosition.y);

	}


	public String getPieceType() {
		return "shark";
	}

	public Point getLastPosition(){ return piecePositions.get((theMovementNumber) - 1) ;}


	public boolean isAlive(){ return status;}

}
