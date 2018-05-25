package view;

import com.sun.javafx.binding.StringFormatter;
import controller.BoardController;
import controller.PieceController;
import model.Player;
import model.piece.Piece;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.*;

import static javax.swing.text.StyleConstants.FontSize;

public class SquareBoard implements Board, Observer {

    private BoardController boardController;

    private Image[] oceanPieceImages = new Image[4];
    private Image[] junglePieceImages = new Image[4];
    private JPanel panel1 = new JPanel(new BorderLayout(3, 3));
    private JButton[][] squareBoardTiles;
    private int numberOfPiecesInEachTeam = 4;
    private JPanel gameBoard;
    private JScrollPane jScrollPane;
    private int rowState , colState;
    private final JLabel message = new JLabel(
            "Animals are ready to play!");

    // Observable Oblects
    private ArrayList<Piece> teamsMap = new ArrayList<Piece>();

    private ArrayList<Piece> firstTeamArray = new ArrayList<>();
    private  ArrayList<Piece> secondTeamArray = new ArrayList<>();

    private Player player;
    PlayersBoard  playersBoard;




    // default Constructor
    public SquareBoard() {
        this.numberOfPiecesInEachTeam = 4 ;
        squareBoardTiles = new JButton[numberOfPiecesInEachTeam + 1 ][numberOfPiecesInEachTeam + 1];
        drawBoard();
    }

    public SquareBoard(int numberOfPiecesInEachTeam){

        this.numberOfPiecesInEachTeam = numberOfPiecesInEachTeam;
        squareBoardTiles = new JButton[numberOfPiecesInEachTeam + 1 ][numberOfPiecesInEachTeam + 1 ];
        drawBoard();

    }

     // Bind the controller to view through this method
    public void addListener(BoardController boardController){
       this.boardController = boardController;
    }



    // view does not have any data from model to put them on the board boardcontroller has to give them to view via this method
    // MVC design pattern
    public void initializeTeamPieces(ArrayList<Piece>  teamsMap) {
       this.teamsMap = teamsMap;
    }


    public void drawBoard(){
        JFrame jFrame1 = new JFrame("JungleNOceanGame");
        jFrame1.setSize(600,600);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame1.setLocation(dim.width/2-jFrame1.getSize().width/2, dim.height/2-jFrame1.getSize().height/2);

        jFrame1.add(panel1);
        // Ensures JVM closes after frame(s) closed and
        // all non-daemon threads are finished
        jFrame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        jFrame1.setLocationByPlatform(true);

        jFrame1.setVisible(true);

        initializingBoard();

        createPlayersForm("Nasrin",jFrame1.getLocation().x - 350 , jFrame1.getLocation().y );


    }

    public void updateThePiecePosition(Piece piece, int row, int col) {

            squareBoardTiles[col][row].setText(piece.getPieceType());
            squareBoardTiles[col][row].setHorizontalTextPosition(0);
            squareBoardTiles[col][row].setVerticalTextPosition(0);
            System.out.println(piece.getPieceType() + " is to move to this location : row " + row + " col" + col);
      }

    public void updateThePiecePosition(Piece piece, int newRow, int newCol, int oldRow, int oldCol) {

        squareBoardTiles[oldCol][oldRow].setText(" ");
        squareBoardTiles[newCol][newRow].setText(piece.getPieceType());
        squareBoardTiles[newCol][newRow].setHorizontalTextPosition(0);
        squareBoardTiles[newCol][newRow].setVerticalTextPosition(0);

        System.out.println(piece.getPieceType() + " was on the tile : row " + oldRow + " col" + oldCol);

        System.out.println(piece.getPieceType() + " is to move to this location : row " + newRow + " col" + newCol);
    }


    public final void initializingBoard(){

        panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        panel1.add(tools, BorderLayout.PAGE_START);

        Action newGameAction = new AbstractAction("New") {

            public void actionPerformed(ActionEvent e) {
                setupNewGame();
            }
        };


        // TODO - add functionality!
        final Action undoMove = new AbstractAction("UNDO") {
            public void actionPerformed(ActionEvent e) {
                undoMove();
            }
        };

        tools.add(newGameAction);
        tools.add(new JButton("Save")); // TODO - add functionality!
        tools.add(new JButton("Restore")); // TODO - add functionality!
        tools.addSeparator();

        Action quitGame = new AbstractAction("QUIT") {

            public void actionPerformed(ActionEvent e) {
                quiteGame();
            }
        };
        tools.add(quitGame);


        tools.addSeparator();
        tools.add(message);
        tools.addSeparator();
        tools.add(undoMove);
        tools.addSeparator();
        tools.add(new JLabel("undo your momevent up to 3 moves"));


        gameBoard = new JPanel(new GridLayout(0, numberOfPiecesInEachTeam + 1 )) {

            @Override
            public final Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize = null;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension(
                            (int)d.getWidth(),(int)d.getHeight());
                } else if (c!=null &&
                        c.getWidth()>d.getWidth() &&
                        c.getHeight()>d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                // the smaller of the two sizes
                int s = (w>h ? h : w);
                return new Dimension(s,s);
            }
        };
        gameBoard.setBorder(new CompoundBorder(
                new EmptyBorder(5,5,5,5),
                new LineBorder(Color.BLACK)
        ));

        Color ochre = new Color(14,103,124);
        gameBoard.setBackground(ochre);
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.setBackground(ochre);
        boardConstrain.add(gameBoard);
        panel1.add(boardConstrain);

        // create the animal pieces board squares
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int i = 0; i < numberOfPiecesInEachTeam + 1 ; i++) {
            for (int j = 0; j < numberOfPiecesInEachTeam + 1 ; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                // our  pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                squareBoardTiles[j][i] = b;
                if ((j % 2 == 1 && i % 2 == 1)
                        || (j % 2 == 0 && i % 2 == 0)) {

                    squareBoardTiles[j][i].setOpaque(true);
                    squareBoardTiles[j][i].setBackground(Color.BLUE);
                   //  squareBoardTiles[j][i].setBorderPainted(false);

                } else {
                    squareBoardTiles[j][i].setBackground(Color.green);
                    squareBoardTiles[j][i].setOpaque(true);
                  //  squareBoardTiles[j][i].setBorderPainted(false);

                }
                createActionListener(squareBoardTiles[j][i]);
                gameBoard.add(squareBoardTiles[j][i]);
                System.out.println("which tile are we at: " + i + " , " +j);
            }
        }

    }

    public void setTurn(String teamToMove){

            playersBoard.setTurnLabel("It is " + teamToMove + "'s turn to move! ");
         }

    public void play(JButton jButton){

        Piece piecetoPlay = null ;

        if (jButton.getText() != null){
          for (Piece piece : teamsMap)
            if (piece.getPieceType().equals(jButton.getText()))
               piecetoPlay = piece;

          System.out.println("The pressed button is : " + piecetoPlay.getPieceType());
              boardController.play(piecetoPlay);
            //  jButton.setText("");
        }

    }

    public void createActionListener(final JButton jButton){

        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                play(jButton);
                }
            });
    }


    //  TODO - I have to make sure that in the model (here the pieces) the data get reset and update their positions, as well//

    private final void setupNewGame() {

        message.setText("Make your move!");
        // set up the  pieces

        boardController.resetBoard();
        resetGameBoard();

        for(Piece piece : teamsMap)
            if (piece.getType().equalsIgnoreCase("jungle"))
                 firstTeamArray.add(piece);
            else secondTeamArray.add(piece);

        for (int ii = 0; ii < numberOfPiecesInEachTeam ; ii++) {

            Point point = new Point( 0,  ii  ) ;

            squareBoardTiles[ii][0].setText(firstTeamArray.get(ii).getPieceType());
            squareBoardTiles[ii][0].setVerticalTextPosition(0);
            squareBoardTiles[ii][0].setHorizontalTextPosition(0);
            squareBoardTiles[ii][0].setBackground(Color.green);
            squareBoardTiles[ii][0].setOpaque(true);
            boardController.setPiecesInitialPositions(firstTeamArray.get((ii)), point);

        }


        // set up the Jungles pieces
        for (int ii = 0; ii < numberOfPiecesInEachTeam ; ii++) {

            Point point = new Point(  numberOfPiecesInEachTeam ,  ii );

            squareBoardTiles[ii][numberOfPiecesInEachTeam].setText(secondTeamArray.get(ii).getPieceType());
            squareBoardTiles[ii][numberOfPiecesInEachTeam].setVerticalTextPosition(0);
            squareBoardTiles[ii][numberOfPiecesInEachTeam].setHorizontalTextPosition(0);
            squareBoardTiles[ii][numberOfPiecesInEachTeam].setBackground(Color.blue);
            squareBoardTiles[ii][numberOfPiecesInEachTeam].setOpaque(true);
            boardController.setPiecesInitialPositions(secondTeamArray.get(ii), point);

        }
    }

    public void resetGameBoard(){

        for( int i = 0 ; i < numberOfPiecesInEachTeam + 1; i ++)
            for( int j = 0 ; j < numberOfPiecesInEachTeam +1 ; j++)
            {
                squareBoardTiles[j][i].setText("");
            }
    }

    public void colourBoard(Color color) {

    }


    public void createPlayersForm(String userSelectedTeamName , int positionX , int positioinY){

        playersBoard = new PlayersBoard(positionX,positioinY);

    }

    // TODO - add functionality!
    public void undoMove(){
      boardController.playerUnDo();

    }

    @Override
    public void update(Observable o, Object arg) {
        for (Piece piece : teamsMap)
         if(o == piece) {
            ArrayList<Point > piece_Old_New_Positions = piece.getPieceMovementPosition();
            updateThePiecePosition( piece , piece_Old_New_Positions.get(0).x , piece_Old_New_Positions.get(0).y,
                    piece_Old_New_Positions.get(1).x , piece_Old_New_Positions.get(1).y );
            break;
        }
    }


    public void quiteGame(){
        System.exit(0);
    }
}
