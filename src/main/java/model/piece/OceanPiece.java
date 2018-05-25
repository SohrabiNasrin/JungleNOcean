package model.piece;

import java.awt.*;
import java.util.ArrayList;

public interface OceanPiece extends Piece{

    ArrayList<Point> move(int diceRoll);

    void capture(Piece piecetoCapture);

    int getRow();

    int getColumn();

    String getPieceType();

    boolean isAlive();
    void setPieceRow(int row);
    void setPieceColumn(int column);
    void setPiecePositions( Point piecePosition) ;
    ArrayList<Point> getPieceMovementPosition();
    void unDo(Point currentPosition, Point prevoiusPosition);
    default String getType() { return "Ocean";}
    void submitMove(ArrayList<Point> pieceMovementPosition);

}
