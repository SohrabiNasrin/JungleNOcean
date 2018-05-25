package view;

import controller.BoardController;
import model.piece.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observer;

public interface Board extends Observer {

    void drawBoard();
    void updateThePiecePosition(Piece pieceName , int col , int row);
    void updateThePiecePosition(Piece pieceName , int newRow , int newCol , int oldRow , int oldCol);

    void colourBoard(Color color);
    void addListener(BoardController boardController);
    void initializeTeamPieces(ArrayList<Piece> teamsMap);
    void setTurn(String teamToMove);

}
