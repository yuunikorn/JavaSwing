package cs260.routemodel;

import java.util.*;
import java.util.Iterator;
import java.util.Vector;
import cs260.graph.Graph;
import cs260.graph.GraphFactory;


/**
 * A document which holds data for the system and interfaces with various component as necessary.
 *
 * @author Bennie Lopez, Yu Chang Ou, Brian Zick
 * @version 1
 *
 */
 public class RouteManager extends Observable{
    private ArrayList<RouteSegment> all_route_segments;
    private Route selected_segments;
    private ArrayList<RouteSegment> potential_routes;
    private Graph<String> route_segments_for_pathing;
    private Vector<RouteListener> listeners;



     /*
      * Create a RouteManager.
      */
     public RouteManager(){
        all_route_segments = new ArrayList<RouteSegment>();
        selected_segments = new Route();
        potential_routes = new ArrayList<RouteSegment>();
        route_segments_for_pathing = GraphFactory.createGraph();
        listeners = new Vector<RouteListener>();
     }

     /**
      * Change the label of the passed RouteSegment to the passed new label.
      *
      * @param segment the segment to change the label of
      * @param new_label the new label for the segment
      */
     public void changeSegmentLabel(RouteSegment segment, String new_label){
         segment.setLabel(new_label); // call the method on the passed object

         this.setChanged();
         this.notifyObservers(); // tell all observers to update // tell all observers to update
     }

     /**
      * Change the end point name of the passed RouteSegment to the passed new end point
      *
      * @param segment the segement to change the label of
      * @param new_end_point the new end point name for the segment
      */
      public void changeSegmentEndPoint(RouteSegment segment, String new_end_point){
        segment.setEndPoint(new_end_point);

        this.setChanged();
        this.notifyObservers();
      }

      /**
       * Change the end point name of the passed RouteSegment to the passed new end point
       *
       * @param segment the segement to change the label of
       * @param new_start_point the new start point name for the segment
       */
       public void changeSegmentStartPoint(RouteSegment segment, String new_start_point){
         segment.setStartPoint(new_start_point);
       }

     /**
      * Add the passed RouteSegment.
      *
      * @param new_segment the segment to add
      * @return the added route segment
      */
     public RouteSegment addRouteSegment(RouteSegment new_segment){
         System.out.println(new_segment);
         all_route_segments.add(new_segment); // record that a new RouteSegment was added
         route_segments_for_pathing.addEdge(new_segment.getStartPoint(), new_segment.getEndPoint()); // add this segment to the graph for pathing, if points do not exist the Graph creates them

         determinePotentialRoutes();
         System.out.println(this.hasChanged());
         this.setChanged();
         this.notifyObservers(); // tell all observers to update
         System.out.println(this.hasChanged());
         return new_segment;
     }

     /**
      * Remove the passed RouteSegment.
      *
      * @param old_segment the segment to add
      * @return the removed route segment
      */
     public RouteSegment removeRouteSegment(RouteSegment old_segment){
         all_route_segments.remove(old_segment); // record that a new Route segment was added

         String old_segment_start = old_segment.getStartPoint();
         String old_segment_end = old_segment.getEndPoint();

         route_segments_for_pathing.removeEdge(old_segment_start, old_segment_end); //remove the possible path

         if(route_segments_for_pathing.degree(old_segment_start) == 0){ // ensure no other paths connect to this vertex
             try {
                 route_segments_for_pathing.removeVertex(old_segment_start);
             } catch (RuntimeException e) {
                 // start point is already not in the graph. huh!
             }
         }

         if(route_segments_for_pathing.degree(old_segment_end) == 0){  // ensure no other paths connect to this vertex
             try {
                 route_segments_for_pathing.removeVertex(old_segment_end);
             } catch (RuntimeException e) {
                 // end point is already not in the graph. huh!
             }
         }

         determinePotentialRoutes();

         this.setChanged();
         this.notifyObservers(); // tell all observers to update
         notifyListeners();

         return old_segment; //return the removed segment
     }

    /**
      * Select the route segment that the user chooses.
      *
      * @param selected_segment the segment the user has selected
      */
     public void selectSegment(RouteSegment selected_segment){
         selected_segments.addRouteSegment(selected_segment); //convey that the segment is selected

         determinePotentialRoutes();

         this.setChanged();
         this.notifyObservers(); // tell all observers to update
         notifyListeners();
     }

     /**
      * Deselect the route segment that the user chooses.
      *
      * @param delected_segment the segment the user has chosen to deselect
      */
     public void deselectSegment(RouteSegment selected_segment){
         selected_segments.removeRouteSegment(selected_segment); //convey that the segment is deselected

         determinePotentialRoutes();

         this.notifyObservers(); // tell all observers to update
         notifyListeners();
     }

     /**
      * Compute the potential routes given currently selected segments.
      */
     private void determinePotentialRoutes(){
         potential_routes.clear(); // get rid of all previous potential routes

         int num_selected_segments = selected_segments.numSegments();

         if (num_selected_segments > 0){
             RouteSegment curr_end_segment = selected_segments.getEndRouteSegment(); //find the current end segment
             String curr_end_point = curr_end_segment.getEndPoint(); // get the end point of that segment

             ArrayList<String> potential_route_end_points = (ArrayList<String>)route_segments_for_pathing.adjacentTo(curr_end_point); // find all vertices adjacent to that vertex, those are legal starting points

             for(RouteSegment segment : all_route_segments){ // iterate over all route segments
                 for(String potential_next_point : potential_route_end_points){ // and iterate through all starting points for each segment
                     if(segment.getEndPoint().equals(potential_next_point) && segment.getStartPoint().equals(curr_end_point)){ //if a segment starts with the current end point and is adjancent, deem it possible
                         potential_routes.add(segment);
                     }
                 }
             }
         } else {
             potential_routes.addAll(all_route_segments); // if there are no selected route segments, all segments are potential routes
         }

         this.notifyObservers(); // tell all observers to update
         notifyListeners();
     }

     /**
      * Return an iterable containing all the currently loaded route segments.
      *
      * @return an iterable of all loaded in route segments
      */
     public Iterable<RouteSegment> getAllRouteSegments(){
         return (ArrayList<RouteSegment>)all_route_segments.clone();
     }

     /**
      * Return an iterable containing all the currently selected route segments.
      *
      * @return an iterable of all selected route segments
      */
     public Iterable<RouteSegment> getSelectedSegments(){
         return selected_segments.getRouteSegments();
     }

     /**
      * Return an iterable containing all the currently selected route segments.
      *
      * @return an iterable of all selected route segments
      */
     public Route getSelectedRoute(){
         return selected_segments;
     }

     /**
      * Return an iterable containing all potential routes.
      *
      * @return an iterable of all potential routes
      */
     public Iterable<RouteSegment> getPotentialRoutes(){
         return (ArrayList<RouteSegment>)potential_routes.clone();
     }

     /**
      * Return an iterable containing all potential routes.
      *
      * @return an iterable of all potential routes
      */
     public Graph<String> getGraph(){
         return route_segments_for_pathing;
     }

     public void addListener(RouteListener l)
     {
         listeners.add(l);
     }

     public void removeListener(RouteListener l)
     {
         listeners.remove(l);
     }

     private void notifyListeners()
     {
         for (RouteListener l : listeners) {
             l.update();
         }
     }
 }
