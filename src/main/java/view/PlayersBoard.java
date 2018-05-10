package view;

import javax.swing.*;
import java.awt.*;

public class PlayersBoard {

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




}
