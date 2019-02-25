package cs260.gui;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import cs260.game_model.TicTacToeGame;
import cs260.game_model.IllegalMoveException;

public class CanvasDisplayControl implements MouseListener, MouseMotionListener, KeyListener{
    private TicTacToeGame game;
    private CanvasDisplay gameDisplay;
    private String altered;

    public CanvasDisplayControl(TicTacToeGame game, CanvasDisplay gameDisplay)
    {
        this.game = game;
        this.gameDisplay = gameDisplay;
        gameDisplay.addMouseListener(this);
        gameDisplay.addMouseMotionListener(this);
        gameDisplay.addKeyListener(this);
        //gameDisplay.addMouseListener(this);
    }

////////////////////mouse listener
    @Override
    public void mouseClicked(MouseEvent e){
      int x = e.getX();
      int y = e.getY();

      //gameDisplay.captureVertex(x,y);
      gameDisplay.checkClicked(x,y);

      //System.out.print("\nCANVASCLICK: " + x + "," + y + "\n");
    }


    @Override
    public void mouseEntered(MouseEvent e){

    }

    @Override
    public void mouseExited(MouseEvent e){

    }

    @Override
    public void mousePressed(MouseEvent e){

    }

    @Override
    public void mouseReleased(MouseEvent e){

    }

////////////////////////mouse motion listener
    @Override
    public void mouseMoved(MouseEvent e) {
      int x = e.getX();
      int y = e.getY();

      gameDisplay.xyCoordListener(x,y);
      }

    @Override
    public void mouseDragged(MouseEvent e) {
      //System.out.print("\nmouseDRAGGED event is called: CANVACDISPLAYCONTROL\n");

      }

//////////////////////key listener
    public void keyTyped(KeyEvent e) {

//      while (e.getKeyChar() != "\n"){
//        char character = e.getKeyChar();
//        altered += character;
//      }

      //gameDisplay.keystrokeListener();
        //displayInfo(e, "KEY TYPED: ");
    }

    /** Handle the key-pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        //displayInfo(e, "KEY PRESSED: ");
    }

    /** Handle the key-released event from the text field. */
    public void keyReleased(KeyEvent e) {
        //displayInfo(e, "KEY RELEASED: ");
    }




}
