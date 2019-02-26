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
import edu.union.adt.graph.*;

import java.util.*;

@RunWith(JUnit4.class)
public class RouteManagerTests
{
    RouteManager r;

    @Before
    public void setUp()
    {
        r = new RouteManager();
    }

    @After
    public void tearDown()
    {
        r = null;
    }

    @Test
    public void construct()
    {
        Iterable<RouteSegment> list_of_route_segments;

        list_of_route_segments = r.getAllRouteSegments();
        Iterator<RouteSegment> iter1 = list_of_route_segments.iterator();
        assertFalse("New route manager has no segments.", iter1.hasNext());

        Iterable<RouteSegment> list_of_selected_segments;

        list_of_selected_segments = r.getSelectedSegments();
        Iterator<RouteSegment> iter2 = list_of_selected_segments.iterator();
        assertFalse("New route manager has no selected segments.", iter2.hasNext());

        Iterable<RouteSegment> list_of_potential_routes;

        list_of_potential_routes = r.getPotentialRoutes();
        Iterator<RouteSegment> iter3 = list_of_potential_routes.iterator();
        assertFalse("New route manager has no potential routes.", iter3.hasNext());

        assertTrue("New route manager's graph is empty.", r.getGraph().isEmpty());
    }

    @Test
    public void addRouteSegment()
    {
        RouteSegment test_route_segment = new RouteSegment("Foo", "Bar", "Foo-Bar", 2.60, "generic_file_path", null);
        r.addRouteSegment(test_route_segment);

        Iterable<RouteSegment> list_of_route_segments;

        list_of_route_segments = r.getAllRouteSegments();
        Iterator<RouteSegment> iter1 = list_of_route_segments.iterator();
        assertTrue("Route manager with one segment has one segment.", iter1.hasNext());
        assertEquals("The one segment is the one that was added.", test_route_segment, iter1.next());
        assertFalse("There was exactly one route segment.", iter1.hasNext());

        Iterable<RouteSegment> list_of_selected_segments;

        list_of_selected_segments = r.getSelectedSegments();
        Iterator<RouteSegment> iter2 = list_of_selected_segments.iterator();
        assertFalse("Route manager with one segment has no selected segments.", iter2.hasNext());

        Iterable<RouteSegment> list_of_potential_routes;

        list_of_potential_routes = r.getPotentialRoutes();
        Iterator<RouteSegment> iter3 = list_of_potential_routes.iterator();
        assertTrue("Route manager with one segment has one potential route.", iter3.hasNext());


        RouteSegment test_route_segment_2 = new RouteSegment("Bar", "Olin", "Reamer-Olin", 2.07, "generic_file_path_2", null);
        r.addRouteSegment(test_route_segment_2);

        list_of_route_segments = r.getAllRouteSegments();
        Iterator<RouteSegment> iter4 = list_of_route_segments.iterator();
        assertTrue("Route manager with two segments has at least one segment.", iter4.hasNext());
        iter4.next();
        iter4.next();
        assertFalse("Route manager with two segments has exactly two segments.", iter4.hasNext());

        list_of_selected_segments = r.getSelectedSegments();
        Iterator<RouteSegment> iter5 = list_of_selected_segments.iterator();
        assertFalse("Route manager with two segments has no selected segments.", iter5.hasNext());

        list_of_potential_routes = r.getPotentialRoutes();
        Iterator<RouteSegment> iter6 = list_of_potential_routes.iterator();
        assertTrue("Route manager with two segments has two potential routes.", iter6.hasNext());
    }

    @Test
    public void removeRouteSegment()
    {
        RouteSegment test_route_segment = new RouteSegment("Foo", "Bar", "Foo-Bar", 2.60, "generic_file_path", null);
        r.addRouteSegment(test_route_segment);
        r.removeRouteSegment(test_route_segment);

        Iterable<RouteSegment> list_of_route_segments;

        list_of_route_segments = r.getAllRouteSegments();
        Iterator<RouteSegment> iter1 = list_of_route_segments.iterator();
        assertFalse("Removing a route segment means the manager has no segments.", iter1.hasNext());

        Iterable<RouteSegment> list_of_selected_segments;

        list_of_selected_segments = r.getSelectedSegments();
        Iterator<RouteSegment> iter2 = list_of_selected_segments.iterator();
        assertFalse("Removing a route segment means the manager has no selected segments.", iter2.hasNext());

        Iterable<RouteSegment> list_of_potential_routes;

        list_of_potential_routes = r.getPotentialRoutes();
        Iterator<RouteSegment> iter3 = list_of_potential_routes.iterator();
        assertFalse("Removing a route segment means the manager has no potential routes.", iter3.hasNext());

        assertTrue("Removing a route segment means the manager's graph is empty.", r.getGraph().isEmpty());

        r.addRouteSegment(test_route_segment);
        RouteSegment test_route_segment_2 = new RouteSegment("Bar", "Olin", "Reamer-Olin", 2.07, "generic_file_path_2", null);
        r.addRouteSegment(test_route_segment_2);
        r.removeRouteSegment(test_route_segment);

        list_of_route_segments = r.getAllRouteSegments();
        Iterator<RouteSegment> iter4 = list_of_route_segments.iterator();
        assertTrue("Route manager with two segments and one removed has one segment.", iter4.hasNext());
        iter4.next();
        assertFalse("There was exactly one route segment.", iter4.hasNext());

        list_of_selected_segments = r.getSelectedSegments();
        Iterator<RouteSegment> iter5 = list_of_selected_segments.iterator();
        assertFalse("Route manager with two segments and one removed has no selected segments.", iter5.hasNext());

        list_of_potential_routes = r.getPotentialRoutes();
        Iterator<RouteSegment> iter6 = list_of_potential_routes.iterator();
        assertTrue("Route manager with two segments and one removed has one potential routes.", iter6.hasNext());
    }

    @Test
    public void getAllRouteSegments()
    {
        Iterable<RouteSegment> list_of_route_segments;

        list_of_route_segments = r.getAllRouteSegments();
        Iterator<RouteSegment> iter1 = list_of_route_segments.iterator();

        assertTrue("A route manager with no segments returns an iterable collection of segments", list_of_route_segments instanceof Iterable);
        assertFalse("A route manager with no segments returns an iterable collection of segments with nothing in it", iter1.hasNext());

        RouteSegment test_route_segment = new RouteSegment("Foo", "Bar", "Foo-Bar", 2.60, "generic_file_path", null);
        r.addRouteSegment(test_route_segment);

        list_of_route_segments = r.getAllRouteSegments();
        Iterator<RouteSegment> iter2 = list_of_route_segments.iterator();

        assertTrue("A route manager with one segment returns an iterable collection of segments", list_of_route_segments instanceof Iterable);
        assertTrue("A route manager with one segment returns an iterable collection of segments with something in it", iter2.hasNext());
        assertEquals("A route manager with one segment returns a collection with the correct segment in it", iter2.next(), test_route_segment);
        assertFalse("A route manager with one segment has exactly one thing in it.", iter2.hasNext());
    }

    @Test
    public void selectSegment()
    {
        Iterable<RouteSegment> list_of_selected_segments;

        list_of_selected_segments = r.getSelectedSegments();
        Iterator<RouteSegment> iter1 = list_of_selected_segments.iterator();

        assertTrue("A route manager with no segments returns an iterable collection of selected segments", list_of_selected_segments instanceof Iterable);
        assertFalse("A route manager with no segments returns an iterable collection of selected segments with nothing in it", iter1.hasNext());

        Route selected_route = r.getSelectedRoute();

        assertEquals("A route manager with no segments returns an empty route.", 0, selected_route.numSegments());
        assertEquals("A route manager with no segments has a route with a null starting segment", null, selected_route.getStartRouteSegment());
        assertEquals("A route manager with no segments has a route with a null ending segment", null, selected_route.getEndRouteSegment());

        RouteSegment test_route_segment = new RouteSegment("Foo", "Bar", "Foo-Bar", 2.60, "generic_file_path", null);
        r.addRouteSegment(test_route_segment);
        r.selectSegment(test_route_segment);

        list_of_selected_segments = r.getSelectedSegments();
        Iterator<RouteSegment> iter2 = list_of_selected_segments.iterator();

        assertTrue("A route manager with one segment returns an iterable collection of selected segments", list_of_selected_segments instanceof Iterable);
        assertTrue("A route manager with one segment returns an iterable collection of selected segments with at least one thing in it", iter2.hasNext());
        assertEquals("A route manager with one segment returns an iterable collection of selected segments with the passed segment in it", test_route_segment, iter2.next());
        assertFalse("A route manager with one segment returns an iterable collection of selected segments with exactly one thing in it", iter2.hasNext());

        selected_route = r.getSelectedRoute();

        assertEquals("A route manager with one segment returns a route with one segment.", 1, selected_route.numSegments());
        assertEquals("A route manager with one segment has a route with the selected segment as the starting segment", test_route_segment, selected_route.getStartRouteSegment());
        assertEquals("A route manager with one segment has a route with the selected as the ending segment", test_route_segment, selected_route.getEndRouteSegment());
    }
}
