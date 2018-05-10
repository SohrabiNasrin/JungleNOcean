package controller;

import java.util.ArrayList;

import model.Player;
import view.MainBoard;


//  Main Cotroller is only responsible for creating BoardController, DiceController and PieceController, So the Single responsibilty design has been applied

public class MainController {

    public static void main(String[] args) {
		
		PieceController pieceController = new PieceController();
		DiceController diceController = new DiceController();
		BoardController boardController = new BoardController( "SquareBoard" , 5 , diceController, pieceController);

	}


}
