package design;


import controller.PieceController;
import model.piece.Piece;

import java.awt.*;
import java.util.ArrayList;

// Concrete Command
public class MoveCommand implements Command{

    private Point previousPosition;
    private ArrayList<Point> piecePositions = new ArrayList<>();
    private Point targetPosition;
    private Piece killedPiece;
    private Point killedPiecePosition;
    private Piece piece;
    private int movement;

    // the reciever in Comamand Design Pattern
    private PieceController pieceController;

    public MoveCommand(Piece piece , int movement , PieceController piececontroller){
        this.movement = movement;
        this.piece = piece;
        this.pieceController = piececontroller;
    }

    @Override
    public void execute() {
        piecePositions = pieceController.move(movement, piece);
        this.targetPosition = new Point(piecePositions.get(0).x , piecePositions.get(0).y);
        this.previousPosition = new Point(piecePositions.get(1).x , piecePositions.get(1).y);
        try {
            if ((killedPiece = pieceController.getCapturedPiece()) != null)
                killedPiecePosition = new Point(killedPiece.getRow() , killedPiece.getColumn());

        }catch (NullPointerException e){}
      }

    @Override
    public void unDo() {

        pieceController.unDo(targetPosition, previousPosition , piece);
        if(killedPiece != null){
            pieceController.releaseCapturedPiece(killedPiece, killedPiecePosition);
        }
    }


}
