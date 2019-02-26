package edu.union.bikeride.manager.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses
({
    RouteManagerTests.class,
    RouteTests.class,
    CSVHandlerTests.class
})
public class BikeManagerTestSuite
{ // no implementation needed; above annotations do the work.
}
