package design;

//import com.google.java.contract.Ensures;
//import com.google.java.contract.Requires;
import view.Board;
import view.SquareBoard;


// Creating The Board based on the requested type
// factroy design pattern has been applied here.


public class BoardFactory {

    Board board;

   // @Requires("boardType != null")
   // @Ensures("squareBoard != null")
    public Board createBoard(String boardType, int numberOfPieces){
        Board squareBoard;
        if (boardType.equals("SquareBoard")){
            squareBoard = new SquareBoard(numberOfPieces);
            return squareBoard;
        }

        // if we have more types of board it can be created here.
        else {
            squareBoard = new SquareBoard(numberOfPieces);
            return squareBoard;
        }


    }
}
