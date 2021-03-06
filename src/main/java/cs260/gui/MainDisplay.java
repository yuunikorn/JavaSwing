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
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.SwingUtilities;

///////////NOT MINE////////////////////
import cs260.routemodel.*;
import cs260.gui.gui_methods.*;
import cs260.graph.Graph;
import cs260.gui.CanvasDisplay;
import cs260.routemodel.RouteListener;


public class MainDisplay extends JComponent implements RouteListener {
	private RouteManager game;
	private CanvasDisplayControl mouseController;
	private OpenSaveOptions CanOpener;
	private JLabel output = new JLabel("XYcoordinates");
	private CanvasDisplay canvas;

	private static int WIDTH = 200;
	private static int GAP = 20;
	private static int LINE_WIDTH = 5;


	public MainDisplay(RouteManager game){
		this.game = game;
		//mouseController = new CanvasDisplayControl(game, this);

		setSize(new Dimension(WIDTH*4, WIDTH*3));
		setPreferredSize(new Dimension(WIDTH*4, WIDTH*3));
		setLayout(new BorderLayout());

		this.add(menubar(), BorderLayout.NORTH);
		this.add(sidebar(), BorderLayout.WEST);

		CanvasDisplay canvas = new CanvasDisplay(game);
		this.add(canvas, BorderLayout.CENTER);
		this.add(canvas.posUpdate(),BorderLayout.PAGE_END);



	}




	private JPanel sidebar(){

		JPanel panel = new JPanel();
		//panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		//panel.setLayout(new GridBagLayout());

		GridLayout experimentLayout = new GridLayout(10,1);
		panel.setLayout(experimentLayout);

		//panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setSize(WIDTH/3, WIDTH*3);

		//panel.add(new JSeparator(JSeparator.VERTICAL));
		panel.add(new JSeparator(JSeparator.VERTICAL));
		panel.add(new JButton("Save Name"));
		panel.add(new JButton("Vertex"));
		panel.add(new JButton("Edge"));
		panel.add(new JButton("Rename"));
		panel.add(new JButton("insert"));
		panel.add(new JButton("something"));
		panel.add(new JButton("here"));
		panel.add(new JButton("Clear All"));

		//canvasImage = new BufferedImage(50,30,BufferedImage.TYPE_INT_ARGB);
		//panel.add(canvasImage);


		return panel;
	}


	private JMenuBar menubar(){
		//JPanel panel1 = new JPanel(new CardLayout());
		//panel1.setSize(400, 400);

		JMenuBar menubar = new JMenuBar();
		menubar.setSize(WIDTH*10, WIDTH/5);

		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");

		JButton importbutton = new JButton("Import");
		JButton exportbutton = new JButton("Export");

		JMenu impMenu = new JMenu("Partial Images");
		JMenuItem newsMenuItem = new JMenuItem("Import Image");
		JMenuItem bookmarksMenuItem = new JMenuItem("Export Image");
		//JMenuItem importMailMenuItem = new JMenuItem("Import mail...");
		impMenu.add(newsMenuItem);
		impMenu.add(bookmarksMenuItem);
		//impMenu.add(importMailMenuItem);

		//JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem openMenuItem = new JMenuItem("Open");

		JMenuItem saveMenuItem = new JMenuItem("Save");

		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.setToolTipText("Exit application");
		exitMenuItem.addActionListener((event) -> System.exit(0));

		JMenuItem addStartPoint = new JMenuItem("something");

		//fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		//fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);
		fileMenu.setVisible(true);

		editMenu.add(addStartPoint);
		editMenu.addSeparator();
		editMenu.add(impMenu);
		editMenu.setVisible(true);

		//menubar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menubar.add(fileMenu);
		menubar.add(editMenu);
		//menubar.add(new JSeparator(JSeparator.VERTICAL));
		menubar.add(importbutton);
		menubar.add(exportbutton);

		CanOpener.OpenOption(openMenuItem);
		CanOpener.SaveOption(saveMenuItem);
		CanOpener.OpenOption(importbutton);
		CanOpener.SaveOption(exportbutton);

		return menubar;
}


///////////NOT MINE///////////////////
/**
public void update(Observable o, Object arg){
	ArrayList<RouteSegment> all_route_segments = (ArrayList<RouteSegment>)((RouteManager) o).getAllRouteSegments();
	ArrayList<RouteSegment> potential_routes = (ArrayList<RouteSegment>)((RouteManager) o).getPotentialRoutes();
	Graph<String> route_manager_graph = ((RouteManager) o).getGraph();
	HashMap<String, Point> VertexPosition = GraphtoHash.convertGraphVertexPosHash(route_manager_graph);
	HashMap<String, ArrayList<String>> graph = GraphtoHash.convertGraphVertexEdgeHash(route_manager_graph);

	canvas.setVertexPosition(VertexPosition);
	canvas.setGraph(graph);
	canvas.setSegmentsToDisplay(all_route_segments);
	canvas.update();
	this.update();
}**/



public void update(){
	repaint();
	System.out.print("\n Update is called: MainDisplay\n");
}

}
