package cs260.gui;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import cs260.game_model.TicTacToeGame;
import cs260.game_model.IllegalMoveException;

public class CanvasDisplayControl implements MouseListener, MouseMotionListener{
    private TicTacToeGame game;
    private CanvasDisplay gameDisplay;

    public CanvasDisplayControl(TicTacToeGame game, CanvasDisplay gameDisplay)
    {
        this.game = game;
        this.gameDisplay = gameDisplay;
        gameDisplay.addMouseListener(this);
        gameDisplay.addMouseMotionListener(this);
        //gameDisplay.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e){
      int x = e.getX();
      int y = e.getY();

      gameDisplay.captureVertex(x,y);

      System.out.print("\nCANVASCLICK: " + x + "," + y + "\n");
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

    @Override
    public void mouseMoved(MouseEvent e) {
      int x = e.getX();
      int y = e.getY();

      gameDisplay.xyCoordListener(x,y);
      }

    @Override
    public void mouseDragged(MouseEvent e) {
      System.out.print("\nmouseDRAGGED event is called: CANVACDISPLAYCONTROL\n");

      }

}


/**

////////////////
        @Override
        public void mousePressed(MouseEvent arg0) {
            // TODO Auto-generated method stub
            if (activeTool==BasicPaint.SELECTION_TOOL) {
                selectionStart = arg0.getPoint();
            } else if (activeTool==BasicPaint.DRAW_TOOL) {
                // TODO
                draw(arg0.getPoint());
            } else if (activeTool==BasicPaint.TEXT_TOOL) {
                // TODO
                text(arg0.getPoint());
            } else {
                JOptionPane.showMessageDialog(
                        gui,
                        "Application error.  :(",
                        "Error!",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        @Override
        public void mouseReleased(MouseEvent arg0) {
            if (activeTool==BasicPaint.SELECTION_TOOL) {
                selection = new Rectangle(
                        selectionStart.x,
                        selectionStart.y,
                        arg0.getPoint().x,
                        arg0.getPoint().y);
            }
        }

////////////
        @Override
        public void mouseDragged(MouseEvent arg0) {
            reportPositionAndColor(arg0);
            if (activeTool==BasicPaint.SELECTION_TOOL) {
                selection = new Rectangle(
                        selectionStart.x,
                        selectionStart.y,
                        arg0.getPoint().x-selectionStart.x,
                        arg0.getPoint().y-selectionStart.y);
            } else if (activeTool==BasicPaint.DRAW_TOOL) {
                draw(arg0.getPoint());
            }
        }

        @Override
        public void mouseMoved(MouseEvent arg0) {
            reportPositionAndColor(arg0);
        }
        }


        private void reportPositionAndColor(MouseEvent me) {
        String text = "";
        if (activeTool==BasicPaint.SELECTION_TOOL) {
            text += "Selection (X,Y:WxH): " +
                    (int)selection.getX() +
                    "," +
                    (int)selection.getY() +
                    ":" +
                    (int)selection.getWidth() +
                    "x" +
                    (int)selection.getHeight();
        } else {
            text += "X,Y: " + (me.getPoint().x+1) + "," + (me.getPoint().y+1);
        }
        output.setText(text);
        }
////////////////


**/
