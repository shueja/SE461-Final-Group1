package org.psnbtech.keys;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.psnbtech.Direction;
import org.psnbtech.SnakeGame;
abstract class MovementKeyTest {

	SnakeGame game;
	Robot robot;
	Direction dir;
	Direction left;
	Direction right;
	Direction reverse;
	
	int key;
	
	@BeforeEach
	void setUp() {
		game = new SnakeGame();
		
	}
	void testKey(boolean initPaused, boolean initGameOver, List<Direction> initDirections, boolean expectedAdd) {
		try {
			var robot = new Robot();
			game.initGame();
			game.isPaused = initPaused;
			game.logicTimer.isPaused = initPaused;
			game.isGameOver = initGameOver;
			game.directions.clear();
			game.directions.addAll(initDirections);
			int size = game.directions.size();
			Direction previousLast = game.directions.peekLast();
			robot.setAutoDelay(100);
			robot.keyPress(key);
			
			robot.keyRelease(key);
			game.repaint();
			
			if (expectedAdd) {
				assertEquals(size+1, game.directions.size(), "queue size not add");
				assertEquals(dir, game.directions.peekLast(), "added dir");
			} else {
				assertEquals(size, game.directions.size(), "queue size add");
				assertEquals(previousLast, game.directions.peekLast(), "not added dir");
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			fail();
		}
	}
	
	/**
	 * 1,5
	 */
	@Test
	public void testPaused() {
		testKey(true, false, new LinkedList(), false);
	}
	/**
	 * 1,5
	 */
	@Test
	public void testGameOver() {
		testKey(false, true, new LinkedList(), false);
	}
	
	
	/**
	 * 1,2,5
	 */
	@Test
	public void testTooManyDirections() {

		testKey(false, false, List.of(
				left,left,left, reverse), false);
	}
	
	/**
	 * 1,2,3,5
	 */
	@Test
	public void testSameDirection() {
		testKey(false, false, List.of(
				dir), false);
	}
	
	/**
	 * 1,2,3,5
	 */
	@Test
	public void testReverseDirection() {
		testKey(false, false, List.of(
				reverse), false);
	}
	/**
	 * 1,2,3,4,5
	 */
	@Test
	public void testLeft() {
		testKey(false, false, List.of(
				left), true);
	}
	
	/**
	 * 1,2,3,4,5
	 */
	@Test
	public void testRight() {
		testKey(false, false, List.of(
				right), true);
	}

}
