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
  private ArrayList<Object> Selected;
  private Object selectedpoint;

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

      //initialization Graphics test here or add hashmap Graph


            VertexPosition.put("START", new Point(150,250));
            VertexPosition.put("END", new Point(600,100));
            VertexPosition.put("MID", new Point(75,10));
            VertexPosition.put("SEMI", new Point(120,450));

            ArrayList<String> edge = new ArrayList<String>(); //edges associated with specific vertex
            edge.add("END");
            edge.add("MID");
            edge.add("SEMI");
            Graph.put("START", edge);
            Graph.put("END", null);

            ArrayList<Object> Selected = new ArrayList<Object>();
  }


/////////////////// Vertexes
        public void drawVertex(Graphics g){

          for (String vertex : VertexPosition.keySet()) {
            Point position = VertexPosition.get(vertex);
            if (vertex.equals(selectedpoint)) {
            //if (Selected != null){
              //if (Selected.contains(vertex)) {
                g.setColor(Color.RED);
              //}}
            }
            int x = (int)position.getX();
            int y = (int)position.getY();
            g.fillOval( x , y , CIRCLEWIDTH,CIRCLEWIDTH);
            g.setColor(Color.BLACK);

            JTextArea textArea = new JTextArea(vertex);
            this.add(textArea);
            textArea.setBounds(x+CIRCLEWIDTH, y, 40, 20);
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

        public void checkClicked(int x, int y) {

          for (String vertex : VertexPosition.keySet()) {
            Point position = VertexPosition.get(vertex);
            if (x >= position.x && x <= (position.x + CIRCLEWIDTH) && y >= position.y && y <= (position.y + CIRCLEWIDTH)){

              //if (Selected != null){
              //  Selected.add(vertex);
              //}
              selectedpoint = vertex;

              System.out.println(vertex);
            }
          }
          this.update();
        }


        private void dragVertex(){

        }

        private void captureVertexLabel(int x, int y){ //for changing vertex labels
        }

/////////////////// Edges
        private void drawEdge(Graphics g){
          for (String vertex : Graph.keySet()){
            if (Graph.get(vertex) != null){
              for (String edge : Graph.get(vertex)){
                Point start = VertexPosition.get(vertex);
                Point end = VertexPosition.get(edge);
                g.drawLine(start.x + CIRCLERADIUS,start.y + CIRCLERADIUS,end.x + CIRCLERADIUS,end.y + CIRCLERADIUS);
                int midx = (start.x + end.x) / 2;
                int midy = (start.y + end.y) / 2;


                JTextArea textArea = new JTextArea("Route Name");
                this.add(textArea);
                textArea.setBounds(midx, midy, 80, 20);
                }
              }
            }
          }


        public void captureEdge(int x, int y){

          VertexPosition.put(VerPoints, new Point(x,y));
          VerPoints += "1";

          System.out.print("\ncaptureVertex is called\n");
          this.update();
        }


/////////////////// Draws everything we need
public void paintComponent(Graphics g){
    super.paintComponent(g);
    //g.setColor(Color.BLACK);

    drawVertex(g);
    drawEdge(g);
    //drawSelectedVertex(g);
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
