package controller;

import model.piece.jungle.LionPiece;

import java.awt.*;

public class LionPieceController {

    private LionPiece lionPiece;
    private int lionRow,
                lionCol;



    public LionPieceController(){
        lionPiece = new LionPiece();
    }

    public void move(int diceRoll){
        lionPiece.move(diceRoll);
        lionRow = lionPiece.getRow();
        lionCol = lionPiece.getColumn();

    }

    public int getLionRow() {return lionRow;}
    public int getLionCol() {return lionCol;}
    public String getPieceType(){return lionPiece.getPieceType();}

    public void setPiecePositions(int movementNumber , Point piecePosition) {
        lionPiece.setPiecePositions(movementNumber , piecePosition );
    }

    // get the piece position on one position before the last move
    public Point getPieceOldPosition() { return lionPiece.getLastPosition();}



}
