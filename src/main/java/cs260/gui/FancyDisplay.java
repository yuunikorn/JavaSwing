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
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;

import cs260.game_model.TicTacToeGame;
import cs260.game_model.TicTacToeListener;
import cs260.gui_methods.OpenSaveOptions;

public class FancyDisplay extends JComponent implements TicTacToeListener {
	private TicTacToeGame game;
	private TicTacToeControl mouseController;
	private OpenSaveOptions CanOpener;

	private static int WIDTH = 200;
	private static int GAP = 20;
	private static int LINE_WIDTH = 5;


	//	public JFileChooser j = new JFileChooser();


	public FancyDisplay(TicTacToeGame game){
		this.game = game;
		mouseController = new TicTacToeControl(game, this);

		setSize(new Dimension(WIDTH*4, WIDTH*3));
		setPreferredSize(new Dimension(WIDTH*4, WIDTH*3));

		this.add(menubar(), BorderLayout.NORTH);
		this.add(sidebar(), BorderLayout.WEST);
		//this.add(MainCanvas(), BorderLayout.CENTER);

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
		panel.add(new JButton("MEEP"));
		panel.add(new JButton("MOOP"));
		panel.add(new JButton("32"));
		panel.add(new JButton("MOO3P"));
		panel.add(new JButton("2"));
		panel.add(new JButton("MO4OP"));
		panel.add(new JButton("MOrOP"));
		panel.add(new JButton("ffef2f"));

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


		JMenu impMenu = new JMenu("Extention");
		JMenuItem newsMenuItem = new JMenuItem("Import newsfeed list...");
		JMenuItem bookmarksMenuItem = new JMenuItem("Import bookmarks...");
		JMenuItem importMailMenuItem = new JMenuItem("Import mail...");
		impMenu.add(newsMenuItem);
		impMenu.add(bookmarksMenuItem);
		impMenu.add(importMailMenuItem);

		//JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem openMenuItem = new JMenuItem("Open");

		JMenuItem saveMenuItem = new JMenuItem("Save");

		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.setToolTipText("Exit application");
		exitMenuItem.addActionListener((event) -> System.exit(0));

		JMenuItem addStartPoint = new JMenuItem("Add Start Point");

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

/**
private JPanel MainCanvas(){


JPanel pCenter = new JPanel();
Box horizontalBox = new Box(BoxLayout.LINE_AXIS);
//setLayout (new BoxLayout(this, BoxLayout.X_AXIS));
	//Box horizontalBox = Box.createHorizontalBox();
	horizontalBox.add(new JLabel("Left"));
	horizontalBox.add(new JTextField("Middle"));
	horizontalBox.add(new JButton("Right"));
	horizontalBox.setBackground(Color.BLUE);
	horizontalBox.setVisible(true);

	pCenter.add(horizontalBox, BorderLayout.SOUTH);
	pCenter.setSize(300, 300);
	pCenter.setVisible(true);
	//pCenter.setVisible(true);


return pCenter;

}**/


public void update(){
	repaint();
	System.out.print("\n Update is called: FancyDisplay\n");
}
}
