package controller;

import model.piece.Piece;
import model.piece.jungle.DogPiece;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;


// DogPiece Controller is responsible for creating the DogPiece and controlling it. SingleResponsibility + High Cohision have been applied here

public class DogPieceController {

    private DogPiece dogPiece;
    private int dogRow,
                dogCol;

    public DogPieceController(){

        dogPiece = new DogPiece();

    }

    public void move(int diceRoll){
        dogPiece.move(diceRoll);
        dogRow = dogPiece.getRow();
        dogCol = dogPiece.getColumn();

    }

    public int getDogRow() {return dogRow;}
    public int getDogCol() {return dogCol;}
    public String getPieceType(){return dogPiece.getPieceType();}

    public void setPiecePositions(int movementNumber , Point piecePosition) {
        dogPiece.setPiecePositions(movementNumber , piecePosition );
    }

    // get the piece position on one position before the last move
    public Point getPieceOldPosition() { return dogPiece.getLastPosition();}

}
