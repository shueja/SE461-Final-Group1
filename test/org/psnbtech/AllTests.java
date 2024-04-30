package org.psnbtech;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ SidePanelTest.class, SnakeGameTest.class, ClockTest.class })
public class AllTests {

}
