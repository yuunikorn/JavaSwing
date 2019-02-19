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
import cs260.opensave.OpenSaveOptions;

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

		setSize(new Dimension(WIDTH*3, WIDTH*3));
		setPreferredSize(new Dimension(WIDTH*3, WIDTH*3));

		this.add(menubar(), BorderLayout.CENTER);

	}

	private JMenuBar menubar(){
		JPanel panel1 = new JPanel(new CardLayout());
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


public void update(){
	repaint();
	System.out.print("\n Update is called: FancyDisplay\n");
}
}
