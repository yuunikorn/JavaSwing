package cs260.routemodel.handler;

import java.util.*;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import cs260.routemodel.RouteSegment;
import cs260.routemodel.Route;
import cs260.routemodel.RouteManager;

/**
 * A file handler which works with importing and exporting CSVs to and from
 * the file system.
 *
 * @author Bennie Lopez, Yu Chang Ou, Brian Zick
 * @version 1
 *
 */
 public class CSVHandler implements FileHandler {

    private ArrayList<String> imported_files;
    private RouteSegment next;
    private RouteManager manager;

    public static final int HEADERS = 2;
    public static final int MIDDLE_OF_ROW = 2;


    /*
     * Create a CSVHandler.
     */
    public CSVHandler(RouteManager route_manager){
        manager = route_manager;
        imported_files = new ArrayList<String>();
        next = null;
    }

    /**
    *
    */
    public void setNext(RouteSegment nextInChain){
      next = nextInChain;
    }

    /**
    * @return next FileHandler type
    */
    public RouteSegment getNext(){
      return next;
    }

    /**
    * @return label of route from CSV
    */
    public String getLabel(String filePath) {
      int slash = filePath.lastIndexOf('/');
      int dot = filePath.lastIndexOf('.');
      return filePath.substring(slash + 1, dot);
    }

    /**
    * @return start of RouteSegment from CSV filePath
    */
    public String getStart(String filePath) throws FileNotFoundException, IOException
    {
      String label = getLabel(filePath);
      int dash = label.indexOf('-');
      return label.substring(0,dash);
    }

    /**
    * @return number of rows in given csv file
    */
    public long getNumRows(String filepath) throws FileNotFoundException, IOException
    {
      CSVReader reader = new CSVReader(new FileReader(filepath));

      reader.readAll();
      long numRows = reader.getLinesRead();
      reader.close();

      return numRows;
    }

    /**
    * @return end of RouteSegment from CSV filePath
    */
    public String getEnd(String filePath) throws FileNotFoundException, IOException
    {
      String label = getLabel(filePath);
      int dash = label.indexOf('-');
      int dash2 = label.lastIndexOf('-');

      if (dash2 == -1 ) { throw new IllegalArgumentException(); }
      else if (dash2 == dash) { return label.substring(dash + 1); }
      return label.substring(dash + 1, dash2);
    }

    /**
    * @return total distance of route segment, from csv file
    */
    public double getDistance(String filePath) throws FileNotFoundException, IOException
    {
      CSVReader reader = new CSVReader(new FileReader(filePath));

      String toReturn = new String();
      String[] line = null;

      reader.skip(HEADERS);
      while ((line = reader.readNext()) != null)
      {
        toReturn = line[MIDDLE_OF_ROW];
      }
      reader.close();

      return Double.parseDouble(toReturn);
    }

    /**
    * Finds and returns set of directions in a given csv file, excluding start and end headers
    * @return directions of a route segment, given a csv file
    */
    public ArrayList<String> getDirections(String filePath) throws FileNotFoundException, IOException {
      CSVReader reader = new CSVReader(new FileReader(filePath));

      ArrayList<String> directions = new ArrayList<String>();
      String[] line = null;
      reader.skip(HEADERS);

      while ((line = reader.readNext()) != null)
      {
        String str = Arrays.toString(line);
        directions.add(str);
      }
      reader.close();
      return directions;
    }

     /**
      * @param file_path path to CSV file to import
      * @return the created route segment
      */
     public RouteSegment importRouteSegment(String filePath) throws FileNotFoundException, IOException
     {
       String start = getStart(filePath);
       String end = getEnd(filePath);
       String label = getLabel(filePath);
       double distance = getDistance(filePath);
       ArrayList<String> directions = getDirections(filePath);

       RouteSegment newSeg = new RouteSegment(start, end, label, distance, filePath, directions);
       System.out.println("The label is: " + label);
       manager.addRouteSegment(newSeg);
       return newSeg;
     }

     /**
      * @param route_to_export the route to export to CSV
      */
     public String exportRoute(Route route_to_export) throws IOException
     {
         String start_point = route_to_export.getStartPoint();
         String end_point = route_to_export.getEndPoint();

         String returnFile = start_point + "-" + end_point + ".csv";
         CSVWriter csvWriter = new CSVWriter(new FileWriter(returnFile));

         Iterable<RouteSegment> routeSegments = route_to_export.getRouteSegments();

         for (RouteSegment route: routeSegments){
           ArrayList<String> directions = route.getDirections();
           String[] addToFile = new String[directions.size()];
           int i = 0;
           for (String direction: directions){
             addToFile[i] = direction;
             i++;
           }
           csvWriter.writeNext(addToFile);
           csvWriter.close();
        }
         return returnFile;
     }
 }
