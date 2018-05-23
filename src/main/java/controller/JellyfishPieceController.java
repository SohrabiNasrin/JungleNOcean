package controller;

import model.piece.ocean.JellyfishPiece;

import java.awt.*;

public class JellyfishPieceController {

    private JellyfishPiece jellyfishPiece;
    private int jellyfishRow,
                jellyfishCol;



    public JellyfishPieceController(){
        jellyfishPiece = new JellyfishPiece();
    }

    public void move(int rollDice){
        jellyfishPiece.move(rollDice);
        jellyfishRow = jellyfishPiece.getRow();
        jellyfishCol = jellyfishPiece.getColumn();
    }
    public int getJellyfishRow() {return jellyfishRow;}
    public int getJellyfishCol() {return jellyfishCol;}
    public String getPieceType(){return jellyfishPiece.getPieceType();}
    public void setPiecePositions(Point piecePosition) {
        jellyfishPiece.setPiecePositions(piecePosition );
    }


}
