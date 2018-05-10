package model.piece;


// Is the interface for JunglePices and OceanPieces classes which is following the Polymorphism patterns as well as Open-Close-Principle (open for extension and close for modificatiopn)

import java.awt.*;

public interface Piece {

	void move(int diceRoll);
	
	void capture(Piece piecetoCapture);
	
	int getRow();
	
	int getColumn();
	
	String getPieceType();

	void setPieceRow(int row);
	void setPieceColumn(int column);
	void setPiecePositions(int movementNumber , Point piecePosition) ;
	Point getLastPosition();

	boolean isAlive();

	Point rollBack();

	String getType();
}
