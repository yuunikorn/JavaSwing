package cs260.gui.gui_methods;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;

import cs260.routemodel.handler.FileHandler;
import cs260.routemodel.handler.CSVHandler;
import cs260.routemodel.RouteManager;
import cs260.routemodel.RouteSegment;

/**
* @author ouy
*/
public class OpenSaveOptions{

	public static JFileChooser j = new JFileChooser();
	private static CSVHandler file_handler;
	private static RouteManager routemanager;


	public static void OpenOption(AbstractButton b1){

		b1.addActionListener(new ActionListener() { //Open is always avalilble

			@Override
			public void actionPerformed(ActionEvent e) {
				j.setApproveButtonText("Open");
				// Set the mnemonic
				j.setApproveButtonMnemonic('O');
				// Set the tool tip
				j.setApproveButtonToolTipText("Open new file");

				int r = j.showOpenDialog(null);			//for open box

				//drop and pass the name through
				if (r == JFileChooser.APPROVE_OPTION) {

					File file = j.getSelectedFile();
					String fullPath = file.getAbsolutePath();
					System.out.println(fullPath);

					try {
						file_handler.importRouteSegment(fullPath);
					} catch (IOException ex) {
						System.out.println("The file passed is invalid. Please try again.");
					}


				}
			}
		});
		//System.out.print("\nOpenOption is called");
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

					try {
						file_handler.exportRoute(routemanager.getSelectedRoute());
					} catch (IOException ex) {
						System.out.println("The file passed is invalid. Please try again.");
					}


				}
			}
		});
		//System.out.print("\nSaveOption is called");
	}

	public static void CancelOption(AbstractButton b3){	//AbstractButton are JButtons and JMenu items
		b3.addActionListener(new ActionListener() {	//when cancelled on start, program proceeds to load empty canvas

			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		//System.exit(0);
		//System.out.print("\nCancelOption is called");
	}

}
