package org.psnbtech;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Robot;
import java.lang.reflect.Constructor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.psnbtech.SnakeGame;


class ClockTest {

	Clock clock;
	final float cyclesPerSecond = 10.0f;
	@BeforeEach
	void setUp() {
		clock = new Clock(cyclesPerSecond);
	}



	@Test
	void testConstructor() {
		assertEquals(clock.isPaused, false);
		assertEquals(clock.elapsedCycles,0);
		assertEquals(clock.excessCycles,0);
		assertEquals(clock.millisPerCycle, (1.0f / cyclesPerSecond) * 1000);
	}
	
	@Test
	void testSetPausedTrue() {
		clock.setPaused(true);
		assertEquals(clock.isPaused, true);
	}
	@Test
	void testSetPausedFalse() {
		clock.setPaused(false);
		assertEquals(clock.isPaused, false);
	}
	
	@Test
	void testIsPausedTrue() {
		clock.isPaused = true;
		assertEquals(clock.isPaused(), true);
	}
	@Test
	void testIsPausedFalse() {
		clock.isPaused = false;
		assertEquals(clock.isPaused(), false);
	}
	
	@Test
	void testPeekElapsedCycleTrue() {
		clock.elapsedCycles = 1;
		assertEquals(clock.peekElapsedCycle(), true);
	}
	@Test
	void testPeekElapsedCycleFalse() {
		clock.elapsedCycles = 0;
		assertEquals(clock.peekElapsedCycle(), false);
	}
	
	@Test
	void testHasElapsedCycleTrue() {
		clock.elapsedCycles = 1;
		assertEquals(clock.hasElapsedCycle(), true);
		assertEquals(clock.elapsedCycles, 0);
	}
	@Test
	void testHasElapsedCycleFalse() {
		clock.elapsedCycles = 0;
		assertEquals(clock.hasElapsedCycle(), false);
	}
	
	@Test
	void testUpdatePaused() {
		var prevLast = clock.lastUpdate;
		clock.isPaused = false;
		clock.update();
		var updateTime =clock.lastUpdate;
		// TODO: test if things changed
	}
	
	@Test
	void testUpdateUnpaused() {
		var prevLast = clock.lastUpdate;
		clock.isPaused = false;
		clock.update();
		var updateTime =clock.lastUpdate;
		// TODO: test if things changed
	}
	

}
