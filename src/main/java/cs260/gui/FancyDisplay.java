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

public class FancyDisplay extends JComponent implements TicTacToeListener {
  private TicTacToeGame game;
	private TicTacToeControl mouseController;
	private static int WIDTH = 200;
	private static int GAP = 20;
	private static int LINE_WIDTH = 5;

	public JFileChooser j = new JFileChooser();

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
			menubar.setSize(WIDTH*3, WIDTH/4);

			JMenu fileMenu = new JMenu("Start");

			JMenu importbutton = new JMenu("Import");
					/**importbutton.addActionListener(new ActionListener(){
						@Override
				    public void actionPerformed(ActionEvent e) {
							j.showOpenDialog(null);
						}});**/
			JMenu exportbutton = new JMenu("Export");
					/**exportbutton.addActionListener(new ActionListener(){
						@Override
				    public void actionPerformed(ActionEvent e) {
						 j.showSaveDialog(null);
					 }});**/

      JMenu impMenu = new JMenu("Extention");

			JMenuItem newsMenuItem = new JMenuItem("Import newsfeed list...");
			JMenuItem bookmarksMenuItem = new JMenuItem("Import bookmarks...");
			JMenuItem importMailMenuItem = new JMenuItem("Import mail...");

				impMenu.add(newsMenuItem);
        impMenu.add(bookmarksMenuItem);
        impMenu.add(importMailMenuItem);

        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setToolTipText("Exit application");
        exitMenuItem.addActionListener((event) -> System.exit(0));

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(impMenu);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
				fileMenu.setVisible(true);

        menubar.add(fileMenu);
				menubar.add(importbutton);
				menubar.add(exportbutton);


				return menubar;
		}


		public void update(){
			repaint();
			System.out.print("\n Update is called: FancyDisplay\n");
		}
}
