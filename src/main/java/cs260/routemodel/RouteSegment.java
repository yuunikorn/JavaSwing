package cs260.routemodel;

import java.util.*;

/**
 * A route segment which holds data contained within a cue sheet and allows
 * a user to access it and modify it as necessary.
 *
 * @author Bennie Lopez, Yu Chang Ou, Brian Zick
 * @version 1
 *
 */
 public class RouteSegment {
     private String start_point;
     private String end_point;
     private String label;
     private double distance;
     private String file_path;
     private ArrayList<String> directions;
     private ArrayList<Object> additions;

     /*
      * Create a route segment.
      */
     public RouteSegment(String start, String end, String newLabel, double dist, String file, ArrayList<String> newDirections){
       start_point = start;
       end_point = end;
       label = newLabel;
       distance = dist;
       file_path = file;
       directions = newDirections;
     }

     /**
      * @return the start point of the route segment
      */
     public String getStartPoint(){
         return start_point;
     }

     /**
      * @return the end point of the route segment
      */
     public String getEndPoint(){
         return end_point;
     }

     /**
      * @param the new end point name
      */
     public void setStartPoint(String new_start_point){
       start_point = new_start_point;
     }

     /**
      * @param the new end point name
      */
     public void setEndPoint(String new_end_point){
       end_point = new_end_point;
     }

     /**
      * @return the label of the route segment
      */
     public String getLabel(){
         return label;
     }

     /**
     * @param new_label the new label for the route segment
     */
     public void setLabel(String new_label){
       label = new_label;
     }

     /**
      * @return the distance of the distance of the route segment
      */
     public double getDistance(){
         return distance;
     }

     /**
      * @return the directions contained in the cue sheet
      */
     public ArrayList<String> getDirections(){
         return directions;
     }
 }
