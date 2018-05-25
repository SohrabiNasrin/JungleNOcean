package view;

import design.GameObserver;
import design.GameSubject;

import javax.swing.*;

public class PlayersBoard implements GameObserver {

    private JPanel panel1 ;
    private JLabel lion ;
    private JLabel userTeam ;
    private JLabel opponentTeam;
    private JLabel lionMove;
    private JLabel TurtleMove;
    private JLabel rabbitMove;
    private JLabel turtle;
    private JLabel rabbit;
    private JLabel dog;
    private JLabel dogMove;
    private JLabel dolphin;
    private JLabel dolphinMove;
    private JLabel shark;
    private JLabel sharkMove;
    private JLabel jellyfish;
    private JLabel jellyfishMove;
    private JLabel electricEel;
    private JLabel electricEelMove;
    private JPanel opponentPanle;
    private JPanel userPanel;
    private JLabel jungleTurn;
    private JLabel oceanTurn;
    private JLabel turnLabel;



    public void setTurnLabel(String turnMessage) {turnLabel.setText(turnMessage);}


    public void setLionMove(String pieceNewPosition){
        lionMove.setText(" hase moved to : " + pieceNewPosition);
    }

    public void setTurtleMove(String pieceNewPosition){
        TurtleMove.setText(" hase moved to : " + pieceNewPosition);
    }

    public void setRabbitMove(String pieceNewPosition){
        rabbitMove.setText(" hase moved to : " + pieceNewPosition);
    }
    public void setDogMove(String pieceNewPosition){
        dogMove.setText(" hase moved to : " + pieceNewPosition);
    }
    public void setDolphinMove(String pieceNewPosition){
        dolphinMove.setText(" hase moved to : " + pieceNewPosition);
    }
    public void setSharkMove(String pieceNewPosition){
        sharkMove.setText(" hase moved to : " + pieceNewPosition);
    }
    public void setJellyfishMove(String pieceNewPosition){
        TurtleMove.setText(" hase moved to : " + pieceNewPosition);
    }
    public void setElectricEelMove(String pieceNewPosition){
        electricEelMove.setText(" hase moved to : " + pieceNewPosition);
    }


    public PlayersBoard(int positionX , int positionY){
        JFrame jFrame1 = new JFrame();
        jFrame1.setLocation(positionX , positionY);
        jFrame1.setSize(350,600);
        jFrame1.add(panel1);
        jFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame1.setVisible(true);

    }


  public static void main(String[] args){

        PlayersBoard playersBoard = new PlayersBoard(100 , 100);

    }

    public PlayersBoard getPlayerBoard(){return this;}


    @Override
    public void update(GameSubject obs, Object... obj) {

    }
}
