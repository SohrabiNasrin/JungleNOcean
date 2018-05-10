//----------------------------------------------------------
//Green Piece 2: Dog
//----------------------------------------------------------
//standard movement
//----------------------------------------------------------

package model.piece.jungle;

import model.piece.JunglePiece;
import model.piece.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


// The model is responsible for its data and only pass them to its controller, its is implementing the Piece interface for Polymorphism design purpose
// and Low Coupling has been applied here



// Cofoja has beeen used to do Apply Design By Contract
//@Invariant("piceColumn<5 && pieceColumn > 0")

public class DogPiece implements JunglePiece {

	private int pieceColumn;
	private int pieceRow;
	private boolean status = true;

	// store the piece movement in order to do the undo later
	private Map<Integer, Point > piecePositions = new HashMap<Integer, Point>();


	// the number of moves that piece does, start with 0
	private static int theMovementNumber = 0;


//	@Requires("diceRoll != null && diceRoll < 6 && diceRoll > 0")

	public void move(int diceRoll) {

		//for (int i = 0; i < diceRoll; i++) {
			if (this.pieceColumn < 4 && diceRoll < 2) {
				this.pieceColumn += diceRoll;
			} else {
				this.pieceRow++;
				this.pieceColumn = 0;
			}
		//}

		Point point = new Point(this.pieceRow , this.pieceColumn);
		this.theMovementNumber++;
		piecePositions.put(theMovementNumber , point);

		System.out.println("DogPiece class, Dog to move" + piecePositions.get(theMovementNumber).x
				+ piecePositions.get(theMovementNumber).y);

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
		System.out.println("The Dog is initialized in position " + piecePosition.x + " and Y is " + piecePosition.y);
	}

	public Point getLastPosition(){
		System.out.println("Dog to move" + piecePositions.get((theMovementNumber) - 1));

		return piecePositions.get((theMovementNumber) - 1) ;

	}

	public String getPieceType() {
		return "dog";
	}

	public boolean isAlive(){ return status;}


}
