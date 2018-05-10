package model.piece;

import java.awt.*;

public interface JunglePiece extends Piece {

    void move(int diceRoll);

    void capture(Piece piecetoCapture);

    int getRow();

    int getColumn();

    String getPieceType();
    void setPieceRow(int row);
    void setPieceColumn(int column);
    void setPiecePositions(int movementNumber , Point piecePosition) ;
    Point getLastPosition();
    Point rollBack();



    boolean isAlive();

    default String getType() {return "Jungle";}


}
