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
import java.awt.*;
import javax.swing.filechooser.*;
import javax.swing.SwingUtilities;

import cs260.game_model.TicTacToeGame;
import cs260.game_model.TicTacToeListener;

/**
 * @author cassa
 */
public class FileOpener
		extends JComponent
		implements TicTacToeListener
{
    private TicTacToeGame game;


		//public JFileChooser j = new JFileChooser();

		// = new JFileChooser("d:", FileSystemView.getFileSystemView());
		public JFileChooser j = new JFileChooser();



    public FileOpener(TicTacToeGame game)
    {
			JFrame frame = new JFrame();
	 		JButton b1 = new JButton();
	 		frame.setSize(500,500);
	 		b1.setSize(100,100);
	 		b1.setVisible(true);
	 		b1.setText("Save");
	 		frame.add(b1);

			b1.addActionListener(new ActionListener() {

			    @Override
			    public void actionPerformed(ActionEvent e) {
						j.setApproveButtonText("New Approve Text");
						// Set the mnemonic
						j.setApproveButtonMnemonic('a');
						// Set the tool tip
						j.setApproveButtonToolTipText("New Approve Tool Tip");
						j.showOpenDialog(null);			//for open box
			    }
					});

	 		frame.setVisible(true);

			/**
			j.setApproveButtonText("New Approve Text");
			// Set the mnemonic
			j.setApproveButtonMnemonic('a');
			// Set the tool tip
			j.setApproveButtonToolTipText("New Approve Tool Tip");
			j.showOpenDialog(null);			//for open box
			//j.showSaveDialog(null);		//for save box
			**/

	 }

		public void update()
		{
			repaint();
			//System.out.print("\nUpdate is called: TicTacToeControl\n");
		}
}
