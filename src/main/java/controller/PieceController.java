package controller;

import model.piece.Piece;
import model.piece.jungle.DogPiece;
import model.piece.jungle.LionPiece;
import model.piece.jungle.RabbitPiece;
import model.piece.jungle.TurtlePiece;
import model.piece.ocean.DolphinPiece;
import model.piece.ocean.ElectricEelPiece;
import model.piece.ocean.JellyfishPiece;
import model.piece.ocean.SharkPiece;
import view.Board;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;


public  class PieceController extends Observable {


    private Map<String , Boolean> piecesStatus = new HashMap<String, Boolean>();
    private Point capturedPiecePosition;
    private boolean checkForCapturedPiece = false;
    private Piece capturedPiece;

    // newely added 30/april/2018
    private ArrayList<Piece> pieceArrayList ;

    public PieceController(){

        pieceArrayList = new ArrayList<>();
        DogPiece dogePiece = new DogPiece();
        pieceArrayList.add(dogePiece);
        LionPiece lionPiece = new LionPiece();
        pieceArrayList.add(lionPiece);
        RabbitPiece rabbitPiece = new RabbitPiece();
        pieceArrayList.add(rabbitPiece);
        TurtlePiece turtlePiece = new TurtlePiece();
        pieceArrayList.add(turtlePiece);
        DolphinPiece dolphinPiece = new DolphinPiece();
        pieceArrayList.add(dolphinPiece);
        ElectricEelPiece electricEelPiece = new ElectricEelPiece();
        pieceArrayList.add(electricEelPiece);
        JellyfishPiece jellyfishPiece = new JellyfishPiece();
        pieceArrayList.add(jellyfishPiece);
        SharkPiece sharkPiece = new SharkPiece();
        pieceArrayList.add(sharkPiece);
    }

    public Boolean checkCollision(Point piecePosition , Piece piece){

        for (Piece pieceName : pieceArrayList ){
            if(!pieceName.getPieceType().equalsIgnoreCase(piece.getPieceType())) {
                if (pieceName.getRow() == piecePosition.x && pieceName.getColumn() == piecePosition.y) {
                    System.out.println("The two pieces lands on the same tile are : " + pieceName.getPieceType() +" , " + piece.getPieceType());
                  if(pieceName.getType() != piece.getType()){
                      capturedPiece = pieceName;
                      capture(pieceName);
                      return false;
                   }else return true;

                }
            }
        }
        return false;

    }

    public ArrayList<Point> move(int movement , Piece piece){

        Point previousPosition = new Point(piece.getRow() , piece.getColumn());

        System.out.println(piece.getPieceType() + " currentLocation " + previousPosition.x + " , " + previousPosition.y);
        ArrayList<Point> pieceMovementPosition = piece.move(movement);
        Point newPiecePosition = new Point(pieceMovementPosition.get(0).x , pieceMovementPosition.get(0).y);

        if (checkCollision(newPiecePosition , piece)){
            piece.setPiecePositions(previousPosition);
            newPiecePosition = previousPosition;
         }

        pieceMovementPosition.set(0,newPiecePosition );
        pieceMovementPosition.set(1,previousPosition );

        piece.submitMove(pieceMovementPosition);

        ArrayList<Point> thePiece_new_old_Positions = new ArrayList<Point>();
        thePiece_new_old_Positions.add(newPiecePosition);
        thePiece_new_old_Positions.add(previousPosition);

        return thePiece_new_old_Positions;

    }

    public void unDo(Point currentPosition, Point previousPosition, Piece piece){
        piece.unDo(currentPosition, previousPosition);
    }

    public void capture(Piece capturedPiece){
       capturedPiecePosition = new Point(capturedPiece.getRow() , capturedPiece.getColumn());
       capturedPiece.capture(capturedPiece);
    }

    public ArrayList<Piece> getPiecesMap(){return pieceArrayList;}

    public Piece getCapturedPiece() throws NullPointerException{
        return capturedPiece ;

    }

    public void setInitialPiecePositions(Piece piece, Point initialPosition){

         piece.setPiecePositions(initialPosition);

    }

    public void releaseCapturedPiece(Piece piece, Point piecePosition){
        piece.release(piecePosition);

    }
}
