package cs260.gui_methods;

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


/**
* @author ouy
*/
public class OpenSaveOptions{
	public static JFileChooser j = new JFileChooser();

	public static void OpenOption(AbstractButton b1){

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
			}
		});
		System.out.print("\nOpenOption is called");
	}

	public static void SaveOption(AbstractButton b2){

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
			}
		});
		System.out.print("\nSaveOption is called");
	}

	public static void CancelOption(AbstractButton b3){	//AbstractButton are JButtons and JMenu items
		b3.addActionListener(new ActionListener() {	//when cancelled on start, program proceeds to load empty canvas

			@Override
			public void actionPerformed(ActionEvent e) {

				//frame.setVisible(false);

			}
		});
		//System.exit(0);
		System.out.print("\nCancelOption is called");
	}

}
