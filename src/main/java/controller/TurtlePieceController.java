package controller;

import model.piece.jungle.TurtlePiece;

import java.awt.*;

public class TurtlePieceController {

    private TurtlePiece turtlePiece;
    private int turtleRow,
                turtleCol;


    public  TurtlePieceController(){
        turtlePiece = new TurtlePiece();
    }

    public void move(int rollDice){
        turtlePiece.move(rollDice);
        turtleRow = turtlePiece.getRow();
        turtleCol = turtlePiece.getColumn();
    }

    public int getTurtleRow() {return turtleCol;}
    public int getTurtleCol() {return turtleCol;}
    public String getPieceType(){return turtlePiece.getPieceType();}

    public void setPiecePositions(int movementNumber , Point piecePosition) {
        turtlePiece.setPiecePositions(movementNumber , piecePosition );
    }

    // get the piece position on one position before the last move
    public Point getPieceOldPosition() { return turtlePiece.getLastPosition();}



}
