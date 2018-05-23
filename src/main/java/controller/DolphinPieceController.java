package controller;

import model.piece.ocean.DolphinPiece;

import java.awt.*;

public class DolphinPieceController {

    private DolphinPiece dolphinPiece;
    private int dolphinRow,
                dolphinCol;

    public DolphinPieceController(){
      dolphinPiece = new DolphinPiece();
    }

    public void move(int rollDice){
        dolphinPiece.move(rollDice);
        dolphinRow = dolphinPiece.getRow();
        dolphinCol = dolphinPiece.getColumn();

    }

    public int getDolphinRow() {return dolphinRow;}
    public int getDolphinCol() {return dolphinCol;}
    public String getPieceType(){return dolphinPiece.getPieceType();}

    public void setPiecePositions(Point piecePosition) {
        dolphinPiece.setPiecePositions( piecePosition );
    }

}
