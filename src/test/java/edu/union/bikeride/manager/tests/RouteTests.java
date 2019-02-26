package edu.union.bikeride.manager.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import edu.union.bikeride.manager.*;

import java.util.*;

@RunWith(JUnit4.class)
public class RouteTests
{
    private Route r;

    @Before
    public void setUp()
    {
        r = new Route();
    }

    @After
    public void tearDown()
    {
        r = null;
    }

    @Test
    public void construct()
    {
        assertEquals("New route has no segments.", 0, r.numSegments());
    }

    @Test
    public void addRouteSegment()
    {
        RouteSegment test_route_segment = new RouteSegment("Foo", "Bar", "Foo-Bar", 2.60, "generic_file_path", null);
        r.addRouteSegment(test_route_segment);
        assertTrue("Adding a route segment causes it to be in the route.", r.hasRouteSegment(test_route_segment));
        assertEquals("Adding a route segment increases the segment count.", 1, r.numSegments());

        boolean thrown = false;
        try {
            r.addRouteSegment(test_route_segment);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }

        assertTrue("An IllegalArgumentException is thrown when an illegal route segment is added.", thrown);
        assertTrue("Adding a route segment again causes it to still be in the route.", r.hasRouteSegment(test_route_segment));
        assertEquals("Adding a route segment again does not increase the segment count.", 1, r.numSegments());

        RouteSegment test_route_segment_2 = new RouteSegment("Bar", "Olin", "Reamer-Olin", 2.07, "generic_file_path_2", null);
        r.addRouteSegment(test_route_segment_2);
        assertTrue("Adding a second route segment causes it to be in the route.", r.hasRouteSegment(test_route_segment_2));
        assertEquals("Adding a second route segment increases the segment count.", 2, r.numSegments());

        // need to add testing for IllegalArgumentException throw
    }

    @Test
    public void removeRouteSegment()
    {
        RouteSegment test_route_segment = new RouteSegment("Foo", "Bar", "Foo-Bar", 2.60, "generic_file_path", null);
        r.addRouteSegment(test_route_segment);
        assertTrue("Adding a route segment causes it to be in the route.", r.hasRouteSegment(test_route_segment));
        assertEquals("Adding a route segment increases the segment count.", 1, r.numSegments());

        r.removeRouteSegment(test_route_segment);
        assertFalse("Removing a route segment causes it to not be in the route.", r.hasRouteSegment(test_route_segment));
        assertEquals("Adding a route segment decreases the segment count.", 0, r.numSegments());

        r = new Route();

        RouteSegment test_route_segment_2 = new RouteSegment("Bar", "Olin", "Reamer-Olin", 2.07, "generic_file_path_2", null);

        r.addRouteSegment(test_route_segment);
        r.addRouteSegment(test_route_segment_2);
        assertTrue("Adding a second route segment causes it to be in the route.", r.hasRouteSegment(test_route_segment_2));
        assertEquals("Adding a second route segment increases the segment count.", 2, r.numSegments());

        r.removeRouteSegment(test_route_segment_2);

        assertFalse("Removing the second route segment added causes it to not be in the route.", r.hasRouteSegment(test_route_segment_2));
        assertEquals("Removing the second route segment decreases the segment count.", 1, r.numSegments());

        r = new Route();

        r.addRouteSegment(test_route_segment);
        r.addRouteSegment(test_route_segment_2);

        r.removeRouteSegment(test_route_segment);

        assertFalse("Removing the first route segment added causes it to not be in the route.", r.hasRouteSegment(test_route_segment));
        assertEquals("Removing the first route segment decreases the segment count.", 1, r.numSegments());

        //need to test for IllegalArgumentException throw
    }

    @Test
    public void getStartPoint()
    {
        RouteSegment test_route_segment = new RouteSegment("Foo", "Bar", "Foo-Bar", 2.60, "generic_file_path", null);
        r.addRouteSegment(test_route_segment);

        String test_start_point = test_route_segment.getStartPoint();
        assertEquals("The start point of the only segment in a route is the start point of the route", test_start_point, r.getStartPoint());

        RouteSegment test_route_segment_2 = new RouteSegment("Bar", "Olin", "Reamer-Olin", 2.07, "generic_file_path_2", null);
        r.addRouteSegment(test_route_segment_2);

        assertEquals("The start point of the first segment in a route with two segments is the start point of the route", test_start_point, r.getStartPoint());
    }

    @Test
    public void getEndPoint()
    {
        RouteSegment test_route_segment = new RouteSegment("Foo", "Bar", "Foo-Bar", 2.60, "generic_file_path", null);
        r.addRouteSegment(test_route_segment);

        String test_end_point = test_route_segment.getEndPoint();
        assertEquals("The end point of the only segment in a route is the end point of the route", test_end_point, r.getEndPoint());

        RouteSegment test_route_segment_2 = new RouteSegment("Bar", "Olin", "Reamer-Olin", 2.07, "generic_file_path_2", null);
        r.addRouteSegment(test_route_segment_2);

        String test_end_point_2 = test_route_segment_2.getEndPoint();
        assertEquals("The end point of the second segment in a route with two segments is the end point of the route", test_end_point_2, r.getEndPoint());
    }

    @Test
    public void hasPoint()
    {
        RouteSegment test_route_segment = new RouteSegment("Foo", "Bar", "Foo-Bar", 2.60, "generic_file_path", null);
        r.addRouteSegment(test_route_segment);

        String test_start_point = test_route_segment.getStartPoint();
        String test_end_point = test_route_segment.getEndPoint();
        assertTrue("The start point of the only segment in a route is in the route", r.hasPoint(test_start_point));
        assertTrue("The end point of the only segment in a route is in the route", r.hasPoint(test_end_point));

        RouteSegment test_route_segment_2 = new RouteSegment("Bar", "Olin", "Reamer-Olin", 2.07, "generic_file_path_2", null);
        r.addRouteSegment(test_route_segment_2);

        test_start_point = test_route_segment_2.getStartPoint();
        test_end_point = test_route_segment_2.getEndPoint();
        assertTrue("The start point of the second segment in a route is in the route", r.hasPoint(test_start_point));
        assertTrue("The end point of the second segment in a route is in the route", r.hasPoint(test_end_point));
    }

    @Test
    public void getRouteSegments()
    {
        Iterable<RouteSegment> list_of_route_segments;

        list_of_route_segments = r.getRouteSegments();
        Iterator<RouteSegment> iter1 = list_of_route_segments.iterator();

        assertTrue("An empty Route returns an iterable collection of segments", list_of_route_segments instanceof Iterable);
        assertFalse("An empty Route returns an iterable collection of segments with nothing in it", iter1.hasNext());

        RouteSegment test_route_segment = new RouteSegment("Foo", "Bar", "Foo-Bar", 2.60, "generic_file_path", null);
        r.addRouteSegment(test_route_segment);

        list_of_route_segments = r.getRouteSegments();
        Iterator<RouteSegment> iter2 = list_of_route_segments.iterator();

        assertTrue("A non-empty Route returns an iterable collection of segments", list_of_route_segments instanceof Iterable);
        assertTrue("A non-empty Route returns an iterable collection of segments with something in it", iter2.hasNext());
        assertEquals("A non-empty Route returns a collection with the correct segment in it", iter2.next(), test_route_segment);
    }
}
