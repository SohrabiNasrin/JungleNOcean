package view;

import controller.BoardController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainBoard {

    private JFrame jFrame1;
    private JPanel panel1;
    private JPanel mainBoard;
    private JButton startButton;
    private JPanel radioPanel;
    private JRadioButton oceanRadioButton;
    private JRadioButton jungleRadioButton;
    private JLabel jLabel1;
    private JComboBox comboBox1;
    private JLabel boardTypeLabel;
    private String boardType;
    private final BoardController boardController;


    public MainBoard(final BoardController boardController) {

          this.boardController = boardController;

          radioPanel.setLayout(new GridLayout(3,0));

          jFrame1 = new JFrame();
          jFrame1.setTitle("JungleNOceanGame");
          jFrame1.setSize(250,250);
          Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
          jFrame1.setLocation(dim.width/2-jFrame1.getSize().width/2, dim.height/2-jFrame1.getSize().height/2);

          jFrame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

          jFrame1.add(panel1);
          jFrame1.setVisible(true);

          startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                boardType = comboBox1.getSelectedItem().toString();
                System.out.print("SelectedBoard is : " + boardType);
                String selectedTeam = "Jungle";
                if (jungleRadioButton.isSelected()) {selectedTeam = "Jungle";}
                else selectedTeam = "Ocean";
                boardController.createGameBoard(boardType , selectedTeam );
                jFrame1.dispose();
            }
        });
    }

    public String getGameBoardType(){
        return boardType;
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
