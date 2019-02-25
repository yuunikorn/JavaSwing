package cs260.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import java.io.*;
import java.awt.*;
import java.awt.Point;
import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.SwingUtilities;

import cs260.game_model.TicTacToeGame;
import cs260.game_model.TicTacToeListener;
import cs260.game_model.IllegalMoveException;

public class CanvasDisplay extends JComponent implements TicTacToeListener{
  private TicTacToeGame game;
  private CanvasDisplayControl mouseController;
  private HashMap<String,ArrayList<String>> Graph;
  private HashMap<String, Point> VertexPosition;

  private static int WIDTH = 200; //from TicTacToe
  private static int GAP = 20;
  private static int LINE_WIDTH = 5;

  private static int CIRCLEWIDTH = 12;  //for size of vertex
  private static int CIRCLERADIUS = CIRCLEWIDTH/2;
  String VerPoints = "1"; //Key of # of vertexes

  private JLabel output = new JLabel("XYcoordinates");


  public CanvasDisplay(TicTacToeGame game){
      this.game = game;
      mouseController = new CanvasDisplayControl(game, this);

      this.setBackground(Color.WHITE);
      this.setSize(new Dimension(WIDTH,WIDTH));
  		this.setPreferredSize(new Dimension(WIDTH,WIDTH));

      //position of vertexes
      VertexPosition = new HashMap<String, Point>(); //<"VertexName", new Point(x,y)> location of vertex

      //position of edges
      Graph = new HashMap<String,ArrayList<String>>(); //<Vertex, ArrayList of edges>
        ArrayList<String> edge = new ArrayList<String>(); //edges associated with specific vertex

//initialization Graphics test
/**
      VertexPosition.put("START", new Point(50,50));
      VertexPosition.put("END", new Point(100,100));

      edge.add("END");
      graph.put("START", edge);
      graph.put("END", null);
**/
  }


    public void drawVertex(Graphics g){

      for (String vertex : VertexPosition.keySet()) {
        Point position = VertexPosition.get(vertex);
        g.fillOval( (int)position.getX() , (int)position.getY() , CIRCLEWIDTH,CIRCLEWIDTH);
        g.setColor(Color.BLACK);
      }

      System.out.print("\ndrawVertex is called\n");
      //this.update();
    }

        public void captureVertex(int x, int y){

          VertexPosition.put(VerPoints, new Point(x,y));
          VerPoints += "1";

          System.out.print("\ncaptureVertex is called\n");
          this.update();
        }

    public void drawEdge(Graphics g){
      

    }


/////////////////// Draws everything we need
public void paintComponent(Graphics g){
    super.paintComponent(g);
    //g.setColor(Color.BLACK);

    drawVertex(g);
}


/////////////////// returns xy coordinates
public void xyCoordListener(int x, int y){
	String text = "";
	text += "X,Y: " + x + "," + y;
	output.setText(text);
}

public JLabel posUpdate(){
  return output;
}
///////////////////


	public void update(){
		repaint();
	}
}




/**


    private void setLineWidth(Graphics g){
        ((Graphics2D) g).setStroke(new BasicStroke(LINE_WIDTH));
    }

    private void drawLines(Graphics g){
        g.drawLine(0, WIDTH, WIDTH*3, WIDTH);
        g.drawLine(WIDTH, 0, WIDTH, WIDTH*3);
        g.drawLine(0, WIDTH*2, WIDTH*3, WIDTH*2);
        g.drawLine(WIDTH*2, 0, WIDTH*2, WIDTH*3);
    }

    private void drawX(Graphics g, int i, int j){
        g.drawLine(i*WIDTH+GAP, j*WIDTH+GAP,
                   i*WIDTH+WIDTH-GAP, j*WIDTH+WIDTH-GAP);
        g.drawLine(i*WIDTH+WIDTH-GAP, j*WIDTH+GAP,
                   i*WIDTH+GAP, j*WIDTH+WIDTH-GAP);
    }

    private void drawO(Graphics g, int i, int j){
        g.drawOval(i*WIDTH + GAP, j*WIDTH + GAP, WIDTH-2*GAP, WIDTH-2*GAP);
    }


**/
