package controller;

import model.piece.ocean.SharkPiece;

import java.awt.*;

public class SharkPieceController {

    private SharkPiece sharkPiece;
    private int sharkRow,
                sharkCol;


    public SharkPieceController(){

        sharkPiece = new SharkPiece();

    }

    public void move(int rollDice){
        sharkPiece.move(rollDice);
        sharkRow = sharkPiece.getRow();
        sharkCol = sharkPiece.getColumn();
    }



    public int getSharkRow() {return sharkRow;}
    public int getSharkCol() {return sharkCol;}
    public String getPieceType(){return sharkPiece.getPieceType();}

    public void setPiecePositions(Point piecePosition) {
        sharkPiece.setPiecePositions(piecePosition );
    }

}
