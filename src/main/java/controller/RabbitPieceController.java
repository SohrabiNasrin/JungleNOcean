package controller;

import model.piece.jungle.RabbitPiece;

import java.awt.*;

public class RabbitPieceController {


    private RabbitPiece rabbitPiece;

    private int rabbitRow,
                rabbitCol;

    private Point piecePositionPoint ;

    public RabbitPieceController(){
        rabbitPiece = new RabbitPiece();
    }



    public void move(int diceRoll){
        rabbitPiece.move(diceRoll);
        rabbitCol = rabbitPiece.getColumn();
        rabbitRow = rabbitPiece.getRow();
    }

   /* public void move(int diceRoll){
        rabbitPiece.move(diceRoll);

        piecePositionPoint new Point()
        rabbitCol = rabbitPiece.getColumn();
        rabbitRow = rabbitPiece.getRow();
    }  */

    public void updateBoard(){

    }
    public int getRabbitRow(){return rabbitRow;}
    public int getRabbitCol(){return rabbitCol;}
    public String getPieceType(){return rabbitPiece.getPieceType();}

    public void setPiecePositions(Point piecePosition) {
        rabbitPiece.setPiecePositions(piecePosition );
    }



}
