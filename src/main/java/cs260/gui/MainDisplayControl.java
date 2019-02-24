package cs260.gui;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import cs260.game_model.TicTacToeGame;
import cs260.game_model.IllegalMoveException;

public class MainDisplayControl implements MouseListener, MouseMotionListener{

    private TicTacToeGame game;
    private MainDisplay gameDisplay;
    //private JLabel output;


    public MainDisplayControl(TicTacToeGame game, MainDisplay gameDisplay){
    this.game = game;
    this.gameDisplay = gameDisplay;
    gameDisplay.addMouseListener(this);
    gameDisplay.addMouseMotionListener(this);
    }


    @Override
    public void mouseClicked(MouseEvent e){

        int x = e.getX();
        int y = e.getY();

        /**
        try {
            int row = gameDisplay.getRow(x);
            int column = gameDisplay.getColumn(y);

            game.placePiece(game.getCurrentPlayer(),row, column);
        } catch (IllegalMoveException ex) {
            // warn user
        }
        **/

        System.out.print("\nmouseClicked event is called: MainDisplayControl: ");
        System.out.print(x + "," + y);
    }

////////////////////////////////////////////////////////////////////////////////
    @Override
    public void mouseMoved(MouseEvent e) {
      int x = e.getX();
      int y = e.getY();

      gameDisplay.xyCoordListener(x,y);

      }




////////////////////////////////////////////////////////////////////////////////
    @Override
    public void mouseDragged(MouseEvent e) {
      System.out.print("\nmouseDRAGGED event is called: MainDisplayControl\n");

      }







    public void mouseEntered(MouseEvent e){

    }

    public void mouseExited(MouseEvent e){

    }

    public void mousePressed(MouseEvent e){

    }

    public void mouseReleased(MouseEvent e){

    }
}
