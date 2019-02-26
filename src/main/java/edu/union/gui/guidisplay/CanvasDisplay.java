package edu.union.gui.guidisplay;

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

import edu.union.gui.game_model.TicTacToeGame;
import edu.union.gui.game_model.RouteListener;
import edu.union.gui.game_model.IllegalMoveException;

import edu.union.bikeride.manager.RouteSegment;

/**
 * A view which fills a frame with information relating to the route segments passed.
 *
 * @author Bennie Lopez, Yu Chang Ou, Brian Zick
 * @version 1
 *
 */
public class CanvasDisplay extends JComponent implements RouteListener {
    private TicTacToeGame game;
    private CanvasDisplayControl mouseController;

    private HashMap<String, ArrayList<String>> graph;
    private HashMap<String, Point> vertexPosition;
    private ArrayList<String> selectedArray;
    private ArrayList<RouteSegment> segments_to_display;

    private static final int WIDTH = 200; //from TicTacToe
    private static final int CIRCLE_WIDTH = 12;  //for size of vertex
    private static final int CIRCLE_RADIUS = CIRCLE_WIDTH/2;
    private static String ver_points = "1"; //Key of # of vertexes

    private JLabel output = new JLabel("XYcoordinates");

    /*
     * Create a new display when given a game.
     */
    public CanvasDisplay(TicTacToeGame game){
        this.game = game;
        mouseController = new CanvasDisplayControl(game, this);

        this.setBackground(Color.WHITE);
        this.setSize(new Dimension(WIDTH, WIDTH));
        this.setPreferredSize(new Dimension(WIDTH, WIDTH));
        selectedArray = new ArrayList<String>();

        //position of vertexes
        vertexPosition = new HashMap<String, Point>(); //<"VertexName", new Point(x,y)> location of vertex

        //position of edges
        graph = new HashMap<String, ArrayList<String>>(); //<Vertex, ArrayList of edges>

        //route segment objects which correspond to edges
        segments_to_display = new ArrayList<RouteSegment>();
    }

    /*
     * Sets the vertices and their positions given the new set.
     *
     * @param new_vertex_position the new set of vertices and their positions
     */
    public void setVertexPosition(HashMap<String, Point> new_vertex_position){
        vertexPosition = new_vertex_position;
    }

    /*
     * Sets the vertices and their edges given the new set.
     *
     * @param new_graph the new graph of vertices and their edges
     */
    public void setGraph(HashMap<String,ArrayList<String>> new_graph){
        graph = new_graph;
    }

    /*
     * Sets the list of route segments given the new set.
     *
     * @param new_segments_to_display the new set of route segments
     */
    public void setSegmentsToDisplay(ArrayList<RouteSegment> new_segments_to_display){
        segments_to_display = new_segments_to_display;
    }

    public void saveVertexNames(){

    }

    public void clearcanvas(){
      this.removeAll();
    }

    /*
     * Draws all vertices given the graphic to draw.
     *
     * @param g the graphic to draw
     */
    public void drawVertex(Graphics g){

        for (String vertex : vertexPosition.keySet()) {
            Point position = vertexPosition.get(vertex);

            if (selectedArray.contains(vertex)){
                g.setColor(Color.RED);
        }

        int x = (int)position.getX();
        int y = (int)position.getY();
        g.fillOval(x, y, CIRCLE_WIDTH, CIRCLE_WIDTH);
        g.setColor(Color.BLACK);


        JTextArea textArea = new JTextArea(vertex);
        textArea.setBounds(x + CIRCLE_WIDTH, y, 40, 20);
        this.add(textArea);
        }
    }

    /*
     * Record the position of a vertex, given its new position
     *
     * @param x the new x coordinate
     * @param y the new y coordinate
     */
    public void captureVertex(int x, int y){
        vertexPosition.put(ver_points, new Point(x,y));
        ver_points += "1";
        this.update();
    }

    /*
     * Determine the selected vertex given the coordinates
     *
     * @param x the clicked x coordinate
     * @param y the clicked y coordinate
     */
    public void selectedVertex(int x, int y){
        for (String vertex : vertexPosition.keySet()) {
            Point position = vertexPosition.get(vertex);
            if (x >= position.x && x <= (position.x + CIRCLE_WIDTH) && y >= position.y && y <= (position.y + CIRCLE_WIDTH)){
                //selectedpoint = vertex;     //change when done
                if (!selectedArray.contains(vertex)){
                    selectedArray.add(vertex);
                } else {
                    selectedArray.remove(vertex);
                }
            }
        }
        if (selectedArray.size() == 1){
            String newvertexPos = selectedArray.get(0);
            Point newpost = new Point(x,y);
            vertexPosition.put(newvertexPos, newpost);
        }
        this.update();
    }

    /*
     * Draws all edges given the graphic to draw.
     *
     * @param g the graphic to draw
     */
    private void drawEdge(Graphics g){
        for (String vertex : graph.keySet()){
            if (graph.get(vertex) != null){
                for (String edge : graph.get(vertex)){
                    Point start = vertexPosition.get(vertex);
                    Point end = vertexPosition.get(edge);
                    g.drawLine(start.x + CIRCLE_RADIUS,start.y + CIRCLE_RADIUS,end.x + CIRCLE_RADIUS,end.y + CIRCLE_RADIUS);
                    int midx = (start.x + end.x) / 2;
                    int midy = (start.y + end.y) / 2;
                    //g.fillRect(midx + 20, midy - 10, 50, 20);
                    String edge_label = "";
                    for(RouteSegment segment : segments_to_display){
                        if(segment.getStartPoint().equals(vertex) && segment.getEndPoint().equals(edge)){
                            if(edge_label.equals("")){
                                edge_label = segment.getLabel();
                            } else {
                                edge_label += ", " + segment.getLabel();
                            }
                        }
                    }
                    System.out.println(edge_label);
                    JTextArea textArea = new JTextArea(edge_label);
                    this.add(textArea);
                    textArea.setBounds(midx, midy, 80, 20);
                }
            }
        }
    }

    private void captureVertexLabel(int x, int y){ //for changing vertex labels
    }

    /*
     * Captures the selected edge
     *
     * @param x the clicked x coordinate
     * @param y the clicked y coordinate
     */
    public void captureEdge(int x, int y){
        vertexPosition.put(ver_points, new Point(x,y));
        ver_points += "1";

        this.update();
    }

    /*
     * Paints the contained components
     *
     * @param x the clicked x coordinate
     * @param y the clicked y coordinate
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.clearcanvas();

        drawVertex(g);
        drawEdge(g);
    }

    /*
     * Listens on the given coordinates
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public void xyCoordListener(int x, int y){
        String text = "";
        text += "X,Y: " + x + "," + y;
        output.setText(text);
    }

    /*
     * Updates the position
     *
     * @return the associated label of what was updated
     */
    public JLabel posUpdate(){
        return output;
    }

    /*
     * Updates the canvas.
     */
	public void update(){
		repaint();
	}
}
