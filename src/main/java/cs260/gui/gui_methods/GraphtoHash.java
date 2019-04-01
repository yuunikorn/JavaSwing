package cs260.gui.gui_methods;

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
import java.awt.Point;
import java.util.*;
import javax.swing.*;
import javax.swing.SwingUtilities;

import cs260.routemodel.RouteManager;
//routemanager has graph and has method that GetGraph

import cs260.graph.Graph;

/**
* @author ouy
*/
public class GraphtoHash{

	public static HashMap<String, Point> convertGraphVertexPosHash(Graph<String> graph_to_convert){
		HashMap<String, Point> VertexPosition = new HashMap<String, Point>();

		int x_coord = 0;
		int y_coord = 0;

		for(String vertex : graph_to_convert.getVertices()){
			VertexPosition.put(vertex, new Point(x_coord, y_coord));

			if (x_coord == y_coord){
				x_coord += 150;
			} else {
				y_coord += 150;
			}
		}
		System.out.println(VertexPosition);
		return VertexPosition;
	}

	public static HashMap<String, ArrayList<String>> convertGraphVertexEdgeHash(Graph<String> graph_to_convert){
		HashMap<String,ArrayList<String>> graph = new HashMap<String,ArrayList<String>>();

		Iterable<String> vertices = graph_to_convert.getVertices();

		for(String vertex : vertices){
			Iterable<String> adjacent_vertices = graph_to_convert.adjacentTo(vertex);

			ArrayList<String> temp_arraylist = new ArrayList<String>();
			for(String adjacent_vertex : adjacent_vertices){
				temp_arraylist.add(adjacent_vertex);
			}
			graph.put(vertex, temp_arraylist);
		}
		System.out.println(graph);
		return graph;
	}
}
