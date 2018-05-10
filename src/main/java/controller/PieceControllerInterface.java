package controller;

import model.piece.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

@FunctionalInterface
public interface PieceControllerInterface {

     Map<String , ArrayList<Point>> move(int movement);

     public default void createPieceControllers() {

          LionPieceController lionPieceController = new LionPieceController();
          DogPieceController dogPieceController = new DogPieceController();
          RabbitPieceController rabbitPieceController = new RabbitPieceController();
          TurtlePieceController turtlePieceController = new TurtlePieceController();
          SharkPieceController sharkPieceController = new SharkPieceController();
          JellyfishPieceController jellyfishPieceController = new JellyfishPieceController();
          ElectriceelPieceController electriceelPieceController = new ElectriceelPieceController();
          DolphinPieceController dolphinPieceController = new DolphinPieceController();

     }

}
