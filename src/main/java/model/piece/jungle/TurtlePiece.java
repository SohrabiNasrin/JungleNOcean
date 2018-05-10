//----------------------------------------------------------
//Green Piece 1 : Turtle
//----------------------------------------------------------
//Only moves when 1 or 6 is rolled
//Once on last row and 6 is rolled then advances to finish 
//----------------------------------------------------------

package model.piece.jungle;
//import com.google.java.contract.Invariant;
//import com.google.java.contract.Requires;
import model.piece.JunglePiece;
import model.piece.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


// Cofoja has beeen used to do Apply Design By Contract
//@Invariant("piceColumn<5 && pieceColumn > 0")

public class TurtlePiece implements JunglePiece {

	private int pieceColumn;
	private int pieceRow;
	private boolean status = true;

	// store the piece movement in order to do the undo later
	private Map<Integer, Point > piecePositions = new HashMap<Integer, Point>();

	// the number of moves that piece does, start with 0
	private static int theMovementNumber = 0;


	//	@Requires("diceRoll != null && diceRoll < 6 && diceRoll > 0")
	public void move(int diceRoll) {

		if (diceRoll == 1) {
			if (this.pieceRow > 0) {
				this.pieceRow--;
			}
		} else if (diceRoll == 6) {
			if (this.pieceRow < 5) {
				this.pieceRow++;
			}
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
		System.out.println("The Turtle is initialized in position " + piecePosition.x + " and Y is " + piecePosition.y);

	}

	public Point getLastPosition(){ return piecePositions.get((theMovementNumber) - 1) ;}


	public String getPieceType() {
		return "turtle";
	}

	public boolean isAlive(){ return status;}

}
