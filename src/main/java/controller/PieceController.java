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

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public  class PieceController {


    // have each team's pieces
    private HashMap< String, ArrayList<String>> piecesMap = new HashMap<String, ArrayList<String>>();

    private ArrayList<String> junglePieces = new ArrayList<String>(),
                              oceanPieces = new ArrayList<String>();

    // Have each piece status to check who is alive and who has been captured
    private Map<String , Boolean> piecesStatus = new HashMap<String, Boolean>();

    // newely added 30/april/2018
    private ArrayList<Piece> pieceArrayList ;
    private HashMap<Piece , ArrayList<Point>>  pieceMovement;




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
                if (pieceName.getRow() == piecePosition.x && piece.getColumn() == piecePosition.y)
                    return true;
            }
        }
        return false;

    }


    public ArrayList<Point> move(int movement , Piece piece){

        ArrayList<Point> pieceMovement = new ArrayList<Point>();

        piece.move(movement);
        Point newPiecePosition ;
        Point oldPiecePosition ;
        ArrayList<Point> thePiece_new_old_Positions = new ArrayList<Point>();

        newPiecePosition = new Point(piece.getRow() , piece.getColumn());

        oldPiecePosition = piece.getLastPosition();

        if (checkCollision(newPiecePosition , piece)){ newPiecePosition = piece.rollBack(); }

        thePiece_new_old_Positions.add(newPiecePosition);
        thePiece_new_old_Positions.add(oldPiecePosition);

       // pieceMovement.add(thePiece_new_old_Positions);

        return thePiece_new_old_Positions ;

    }


    public ArrayList<Piece> getPiecesMap(){return pieceArrayList;}


    public void setInitialPiecePositions(Piece piece, Point initialPosition){

         piece.setPiecePositions(0,initialPosition);

    }
}
