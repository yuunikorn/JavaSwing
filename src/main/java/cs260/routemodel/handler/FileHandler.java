package cs260.routemodel.handler;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import cs260.routemodel.RouteSegment;
import cs260.routemodel.Route;

/**
 * FileHandlers create RouteSegments and export Routes as desiredFileType
 * The filehandler implements chain of responsibility
 *
 * @author Bennie Lopez, Yu Chang Ou, Brian Zick
 * @version 1
 *
 */

public interface FileHandler
{
  /**
  *
  */
  public void setNext(RouteSegment nextInChain);

  /**
  * @return next RouteSegment type to be checked in chain of responsibility
  */
  public RouteSegment getNext();

  /**
   * @param file_path path to CSV file to import
   * @return the created route segment
   */
  public RouteSegment importRouteSegment(String filePath) throws FileNotFoundException, IOException;

  /**
   * @param route_to_export the route to export to CSV
   */
  public String exportRoute(Route route_to_export) throws IOException;

}
