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
		JPanel cards;
		final static String BUTTONPANEL = "Card with JButtons";
    final static String TEXTPANEL = "Card with JTextField";


		//public JFileChooser j = new JFileChooser();

		// = new JFileChooser("d:", FileSystemView.getFileSystemView());
		public JFileChooser j = new JFileChooser();



    public FileOpener(TicTacToeGame game)
    {
			//JPanel comboBoxPane = new JPanel(); //use FlowLayout
			String comboBoxItems[] = { BUTTONPANEL, TEXTPANEL };
			//JComboBox cb = new JComboBox(comboBoxItems);
			//cb.setEditable(false);
			//cb.addItemListener(null);
			//comboBoxPane.add(cb);

			//Create the "cards".
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


			JFrame frame = new JFrame("Please select an option");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//frame.add(comboBoxPane, BorderLayout.PAGE_START);
			frame.add(cards, BorderLayout.CENTER);

			frame.pack();
			frame.setVisible(true);


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
                // set the label to the path of the selected directory
              //  pathname = setText(j.getSelectedFile().getAbsolutePath());
								File file = j.getSelectedFile();
								String fullPath = file.getAbsolutePath();
								System.out.println(fullPath);

								//System.out.print("\npathname");
							}

						frame.setVisible(false);
			    	}
						});

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

								/** //////////allows us to check if user has already made file of same name////////////////
								File xmlFile = new File(chooser.getSelectedFile().toString()+ ".xml");
										if (xmlFile.exists()) {
										    int response = JOptionPane.showConfirmDialog(null, //
										            "Do you want to replace the existing file?", //
										            "Confirm", JOptionPane.YES_NO_OPTION, //
										            JOptionPane.QUESTION_MESSAGE);
										    if (response != JOptionPane.YES_OPTION) {
										        return;
										    }
										}
								//////////////////// **/

								//Addition to this, we need to actually create the file


							}

						frame.setVisible(false);
			    	}
						});


			b3.addActionListener(new ActionListener() {	//when cancelled on start, program proceeds to load empty canvas

			    @Override
			    public void actionPerformed(ActionEvent e) {

						frame.setVisible(false);
			    	}
						});


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
