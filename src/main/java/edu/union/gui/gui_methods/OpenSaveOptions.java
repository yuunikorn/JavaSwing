package edu.union.gui.gui_methods;

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

import java.util.*;
import edu.union.bikeride.manager.CSVHandler;
import edu.union.bikeride.manager.RouteSegment;
import edu.union.bikeride.manager.RouteManager;

/**
* @author ouy
*/
public class OpenSaveOptions {
	public JFileChooser j;
	private CSVHandler csv_handler;
	private RouteManager routemanager;

	public OpenSaveOptions(CSVHandler handler){
		j  = new JFileChooser();
		csv_handler = handler;
		
	}

	public void OpenOption(AbstractButton b1){

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
					try {
						csv_handler.importRouteSegment(fullPath);
					} catch (IOException ex) {
						System.out.println("The file passed is invalid. Please try again.");
					}
					System.out.println(fullPath);
				}
			}
		});
		//System.out.print("\nOpenOption is called");
	}

	public void SaveOption(AbstractButton b2){

		b2.addActionListener(new ActionListener() { //save can save despite being empty

			@Override
			public void actionPerformed(ActionEvent e) {
				j.setApproveButtonText("Save");
				// Set the mnemonic
				j.setApproveButtonMnemonic('s');
				// Set the tool tip
				j.setApproveButtonToolTipText("Save selected routes");

				int r = j.showSaveDialog(null);

				//drop and pass the name through
				if (r == JFileChooser.APPROVE_OPTION) {
					// set the label to the path of the selected directory
					//  pathname = setText(j.getSelectedFile().getAbsolutePath());
					File file = j.getSelectedFile();
					String fullPath = file.getAbsolutePath();
					try {

						csv_handler.exportRoute(routemanager.getSelectedRoute());

					} catch (IOException ex) {
						System.out.println("The file passed is invalid. Please try again.");
					}
					System.out.println(fullPath);
				}
			}
		});
		//System.out.print("\nSaveOption is called");
	}

	public void CancelOption(AbstractButton b3){	//AbstractButton are JButtons and JMenu items
		b3.addActionListener(new ActionListener() {	//when cancelled on start, program proceeds to load empty canvas

			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		//System.exit(0);
		//System.out.print("\nCancelOption is called");
	}
}
