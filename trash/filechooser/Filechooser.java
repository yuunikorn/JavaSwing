package cs260.openfile;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.*;

import cs260.game_model.TicTacToeGame;
import cs260.game_model.TicTacToeListener;

/**
 * @author cassa
 */
public class Filechooser
		extends JComponent
		implements TicTacToeListener
{
    private TicTacToeGame game;

		//public JFileChooser j = new JFileChooser();
		//public JFileChooser j = new JFileChooser("d:", FileSystemView.getFileSystemView());
		public JFileChooser j = new JFileChooser();


    public Filechooser(TicTacToeGame game)
    {
				j.showSaveDialog(null);
			}

		public void update()
		{
			repaint();
			//System.out.print("\nUpdate is called: TicTacToeControl\n");
		}
}
