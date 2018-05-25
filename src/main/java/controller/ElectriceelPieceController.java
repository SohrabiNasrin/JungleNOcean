package controller;

import model.piece.ocean.ElectricEelPiece;

import java.awt.*;

public class ElectriceelPieceController {

    private ElectricEelPiece electricEelPiece;
    private int electricEelRow,
                electricEelCol;


    public ElectriceelPieceController(){
         electricEelPiece = new ElectricEelPiece();
    }

    public void move(int rollDice){
        electricEelPiece.move(rollDice);
    }

    public int getElectricEelRow() {return electricEelRow;}
    public int getElectricEelCol() {return electricEelCol;}
    public String getPieceType(){return electricEelPiece.getPieceType();}

    public void setPiecePositions( Point piecePosition) {
        electricEelPiece.setPiecePositions(piecePosition );
    }

}
