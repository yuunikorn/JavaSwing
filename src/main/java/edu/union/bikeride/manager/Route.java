package edu.union.bikeride.manager;

import java.util.*;

/**
 * A route which holds multiple route segments for a user's file selection.
 *
 * @author Bennie Lopez, Yu Chang Ou, Brian Zick
 * @version 1
 *
 */
 public class Route{
     private String start_point;
     private String end_point;
     private ArrayList<String> route_path;
     private ArrayList<RouteSegment> segments;

     /*
      * Create a route.
      */
     public Route(){
         start_point = "";
         end_point = "";
         route_path = new ArrayList<String>();
         segments = new ArrayList<RouteSegment>();
     }

     /**
      * @param new_start_point add a new start point to the route
      */
     private void setStartPoint(String new_start_point){
         start_point = new_start_point;
     }

     /**
      * @param new_end_point add a new end point to the route
      */
     private void setEndPoint(String new_end_point){
         end_point = new_end_point;
     }

     /**
      * @return the start point of the route
      */
     public String getStartPoint(){
         return start_point;
     }

     /**
      * @return the end point of the route
      */
     public String getEndPoint(){
         return end_point;
     }

     /**
      * @param old_start_point remove the start point if there is one
      */
     private void removeStartPoint(){
         if(start_point != "") {
             start_point = "";
         }
     }

     /**
      * @param old_end_point remove the end point if there is one
      */
     private void removeEndPoint(){
         if(end_point != "") {
             end_point = "";
         }
     }

     /**
      * @param new_route_segment add passed route segment
      */
     public void addRouteSegment(RouteSegment new_route_segment){
         if(this.getStartPoint().equals("") && this.getEndPoint().equals("")){
             this.setStartPoint(new_route_segment.getStartPoint()); // in this case there are no segments currently in the route, so just add and set start and end points
             this.setEndPoint(new_route_segment.getEndPoint());
             segments.add(new_route_segment);
         } else if(this.getEndPoint().equals(new_route_segment.getStartPoint())) {
             this.setEndPoint(new_route_segment.getEndPoint()); // in this case there is at least one segment currently in the route, so add the segment and set the end point accordingly
             segments.add(new_route_segment);
         } else {
             throw new IllegalArgumentException(); // passed route segment is not valid
         }
     }

     /**
      * @param old_route_segment remove passed route segment
      */
     public void removeRouteSegment(RouteSegment old_route_segment){
         if(this.hasRouteSegment(old_route_segment)){
             if(this.getEndPoint().equals(old_route_segment.getEndPoint())){
                 this.setEndPoint(old_route_segment.getStartPoint());
                 segments.remove(old_route_segment);
             }

             if (this.getStartPoint().equals(old_route_segment.getStartPoint())){
                 this.removeStartPoint();
                 segments.remove(old_route_segment);
             }
         } else {
             throw new IllegalArgumentException(); // cannot remove a segment not in the route
         }
     }

     /**
      * Checks if the passed route segment is in the route.
      *
      * @param segment_to_check segment to inquire about
      */
     public boolean hasRouteSegment(RouteSegment segment_to_check){
         return segments.contains(segment_to_check);
     }

     /**
      * Checks if the passed point is in the route
      *
      * @param point_to_check point to inquire about
      */
     public boolean hasPoint(String point_to_check){
         boolean point_is_in = false;

         // for(RouteSegment segment : segments){
         //     if(segment.hasPoint(point_to_check)){
         //         point_is_in = true;
         //     }
         // }
         // Check if RouteSegment has the point passed, commented out because not implemented yet

         for(RouteSegment segment : segments){
             if (point_to_check.equals(segment.getStartPoint()) || point_to_check.equals(segment.getEndPoint())){
                 point_is_in = true;
             }
         }

         return point_is_in;
     }

     /**
      * Returns the first route segment in the route.
      *
      * @return the first route segment in the route
      */
     public RouteSegment getStartRouteSegment(){
         if (this.numSegments() > 0){
             return segments.get(0);
         } else {
             return null;
         }

     }

     /**
      * Returns the last route segment in the route.
      *
      * @return the last route segment in the route
      */
     public RouteSegment getEndRouteSegment(){
         if (this.numSegments() > 0){
             return segments.get(this.numSegments() - 1);
         } else {
             return null;
         }
     }

     /**
      * Returns the number of segments in the route.
      *
      * @return the number of segments in the route
      */
     public int numSegments(){
         return segments.size();
     }

     /**
      * Returns an interable of the segments in the route
      *
      * @return an iterable of the segments in the route
      */
     public Iterable<RouteSegment> getRouteSegments(){
         return (ArrayList<RouteSegment>)segments.clone();
     }
 }
