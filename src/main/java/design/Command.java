package design;

import model.piece.Piece;

public interface Command {

    void execute();
    void unDo();
}
