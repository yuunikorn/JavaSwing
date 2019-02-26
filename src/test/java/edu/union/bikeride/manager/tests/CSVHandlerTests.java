package edu.union.bikeride.manager.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.FileNotFoundException;
import java.io.IOException;
import edu.union.bikeride.manager.*;

import java.util.*;

@RunWith(JUnit4.class)
public class CSVHandlerTests
{
  CSVHandler csvHand;
  RouteManager manager;

  @Before
  public void setUp()
  {
    manager = new RouteManager();
    csvHand = new CSVHandler(manager);
  }

  @After
  public void tearDown()
  {
    csvHand = null;
  }

  @Test
  public void testGetLabel()
  {
    String label = csvHand.getLabel("sample-inputs/SCCC-Schermerhorn+Putnam.csv");
    assertEquals("SCCC-Schermerhorn+Putnam", label);
  }

  @Test
  public void testGetStart() throws FileNotFoundException, IOException
  {
    String start = csvHand.getStart("sample-inputs/SCCC-Schermerhorn+Putnam.csv");
    assertEquals("SCCC", start);
  }

  @Test
  public void testGetEnd() throws FileNotFoundException, IOException
  {
    String end = csvHand.getEnd("sample-inputs/SCCC-Schermerhorn+Putnam.csv");
    assertEquals("Schermerhorn+Putnam", end);
  }

  @Test
  public void testGetDistance() throws FileNotFoundException, IOException
  {
    double distance = csvHand.getDistance("sample-inputs/SCCC-Schermerhorn+Putnam.csv");
    assertEquals(4.13, distance, 0);
  }

  @Test
  public void testGetDirections() throws FileNotFoundException, IOException
  {
    ArrayList<String> directions = csvHand.getDirections("sample-inputs/SCCC-Schermerhorn+Putnam.csv");
    assertEquals("[[Right, Turn right to stay on Mohawk-Hudson Bike-Hike Trail/Mohawk Hudson Bikeway, 1.44], [Left, Turn left toward Rice Rd, 2.6], [Left, Turn left onto Rice Rd, 2.62], [Right, Turn right onto Schermerhorn Rd, 3.15], [End, End of route, 4.13]]", directions.toString());
  }

  @Test
  public void testImportRouteSegment() throws FileNotFoundException, IOException
  {
    RouteSegment seg = csvHand.importRouteSegment("sample-inputs/Currybush+Skyline-MariavilleStore-DuanesburgChurches.csv");

    String start = seg.getStartPoint();
    assertEquals("Currybush+Skyline", start);

    String end = seg.getEndPoint();
    assertEquals("MariavilleStore", end);

    String label = seg.getLabel();
    assertEquals("Currybush+Skyline-MariavilleStore-DuanesburgChurches", label);

    double distance = seg.getDistance();
    assertEquals(10.07, distance, 0);

    ArrayList<String> directions = seg.getDirections();
    assertEquals("[[Left, Turn left onto Skyline Dr, 0], [Right, Turn right onto Duanesburg Churches Rd, 4.44], [Right, Turn right onto Batter St, 7.37], [End, End of route, 10.07]]", directions.toString());
  }

  // @Test
  // public void testExportRoute() throws FileNotFoundException, IOException
  // {
  //     RouteSegment seg = csvHand.importRouteSegment("sample-inputs/Currybush+Skyline-MariavilleStore-DuanesburgChurches.csv");
  //     Route r = new Route();
  //     RouteSegment seg2 = csvHand.importRouteSegment("sample-inputs/Schermerhorn+Putnam-Currybush+Skyline-MariavilleRd.csv");
  //     System.out.println(seg2.getStartPoint());
  //     System.out.println(seg.getStartPoint());
  //     System.out.println(seg2.getEndPoint());
  //     System.out.println(seg.getEndPoint());
  //     r.addRouteSegment(seg2);
  //     r.addRouteSegment(seg);
  //
  //     String s = csvHand.exportRoute(r);
  //
  //     assertEquals("[[[Right, Turn right onto Putnam Rd, 0], [Left, Turn left onto NY-159 E, 1.6], [Right, Turn right onto Currybush Rd, 2.36], [End, End of route, 6.02]], [[Left, Turn left onto Skyline Dr, 0], [Right, Turn right onto Duanesburg Churches Rd, 4.44], [Right, Turn right onto Batter St, 7.37], [End, End of route, 10.07]]]", s.toString());
  // }
}
