package model.piece;


// Is the interface for JunglePices and OceanPieces classes which is following the Polymorphism patterns as well as Open-Close-Principle (open for extension and close for modificatiopn)

import java.util.Observable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observer;

public interface Piece{

	ArrayList<Point> move(int diceRoll);
	
	void capture(Piece piecetoCapture);
	
	int getRow();
	
	int getColumn();
	
	String getPieceType();

	void setPieceRow(int row);
	void setPieceColumn(int column);
	void setPiecePositions( Point piecePosition) ;
	ArrayList<Point> getPieceMovementPosition();
	boolean isAlive();
	void addObserver(Observer ob);
	void release(Point piecePosition);

	void unDo(Point currentPosition, Point prevoiusPosition);

	void submitMove(ArrayList<Point> pieceMovementPosition);

	String getType();
}
