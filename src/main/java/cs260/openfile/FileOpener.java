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
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;

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

		//jpanel object
		public static JFrame frame = new JFrame("Please select an option");

		final static String BUTTONPANEL = "Card with JButtons";
    final static String TEXTPANEL = "Card with JTextField";
		JPanel cards;

		//public JFileChooser j = new JFileChooser();

		// = new JFileChooser("d:", FileSystemView.getFileSystemView());
		//public JFileChooser j = new JFileChooser();

		public FileOpener(TicTacToeGame game){
			OpenFileConstructor();
		}
		//nice popup for whether you want to overwrite file



    private JFrame OpenFileConstructor(){

			String comboBoxItems[] = { BUTTONPANEL, TEXTPANEL };

			JPanel card1 = new JPanel();
			JPanel card2 = new JPanel();

			JButton b1 = new JButton("Open");
			JButton b2 = new JButton("Save");
			JButton b3 = new JButton("Cancel");

			card1.add(b1);
			card1.add(b2);
			card1.add(b3);

			card2.add(new JLabel("Please select and option", JLabel.CENTER));

			cards = new JPanel(new CardLayout());
			cards.add(card1, BUTTONPANEL);
			cards.add(card2, TEXTPANEL);



			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			frame.add(cards, BorderLayout.CENTER);

			frame.pack();
			frame.setVisible(true);

			OpenOption(b1);
			SaveOption(b2);
			CancelOption(b3);

			return frame;
	 }


	 public static void OpenOption(AbstractButton b1){
		 	 JFileChooser j = new JFileChooser();
			 b1.addActionListener(new ActionListener() { //Open is always avalilble

			 @Override
			 public void actionPerformed(ActionEvent e) {
				 j.setApproveButtonText("Open");
				 // Set the mnemonic
				 j.setApproveButtonMnemonic('a');
				 // Set the tool tip
				 j.setApproveButtonToolTipText("New Approve Tool Tip");

				 int r = j.showOpenDialog(null);			//for open box

				 //drop and pass the name through
				 if (r == JFileChooser.APPROVE_OPTION) {

					 File file = j.getSelectedFile();
					 String fullPath = file.getAbsolutePath();
					 System.out.println(fullPath);
				 	 }

				 frame.setVisible(false);
			}
			});
	 		}

	 public static void SaveOption(AbstractButton b2){
		 JFileChooser j = new JFileChooser();
		 b2.addActionListener(new ActionListener() { //save can save despite being empty

				 @Override
				 public void actionPerformed(ActionEvent e) {
					 j.setApproveButtonText("Save");
					 // Set the mnemonic
					 j.setApproveButtonMnemonic('a');
					 // Set the tool tip
					 j.setApproveButtonToolTipText("New Approve Tool Tip");

					 int r = j.showSaveDialog(null);

					 //drop and pass the name through
					 if (r == JFileChooser.APPROVE_OPTION) {
							 // set the label to the path of the selected directory
						 //  pathname = setText(j.getSelectedFile().getAbsolutePath());
							 File file = j.getSelectedFile();
							 String fullPath = file.getAbsolutePath();
							 System.out.println(fullPath);

						 }

					 frame.setVisible(false);
					 }
					 });
	 	  }

			public static void CancelOption(AbstractButton b3){	//AbstractButton are JButtons and JMenu items
						b3.addActionListener(new ActionListener() {	//when cancelled on start, program proceeds to load empty canvas

						@Override
						public void actionPerformed(ActionEvent e) {

							frame.setVisible(false);
							}
							});
			}




		public void update()
		{
			repaint();
			//System.out.print("\nUpdate is called: TicTacToeControl\n");
		}
}
