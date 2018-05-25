package model.piece;



import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public interface JunglePiece extends Piece , Serializable {

    ArrayList<Point> move(int diceRoll);

    void capture(Piece piecetoCapture);

    int getRow();

    int getColumn();

    String getPieceType();
    void setPieceRow(int row);
    void setPieceColumn(int column);
    void setPiecePositions( Point piecePosition) ;
    ArrayList<Point> getPieceMovementPosition();
    void unDo(Point currentPosition, Point prevoiusPosition);

    void addObserver(Observer ob);



    boolean isAlive();

    default String getType() {return "Jungle";}
    void submitMove(ArrayList<Point> pieceMovementPosition);


}
