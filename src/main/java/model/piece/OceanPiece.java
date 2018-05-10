package model.piece;

import java.awt.*;

public interface OceanPiece extends Piece{

    void move(int diceRoll);

    void capture(Piece piecetoCapture);

    int getRow();

    int getColumn();

    String getPieceType();

    boolean isAlive();
    void setPieceRow(int row);
    void setPieceColumn(int column);
    void setPiecePositions(int movementNumber , Point piecePosition) ;
    Point getLastPosition();
    Point rollBack();

    default String getType() { return "Ocean";}

}
