package design;

import model.piece.Piece;

import java.io.Serializable;

public interface Command extends Serializable{

    void execute();
    void unDo();
}
