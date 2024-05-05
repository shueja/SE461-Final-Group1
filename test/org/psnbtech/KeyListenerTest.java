package org.psnbtech;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.lang.reflect.Constructor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.psnbtech.SnakeGame;

class KeyListenerTest {

	SnakeGame game;
	Robot robot;
	@BeforeEach
	void setUp() {
		game = new SnakeGame();
		
	}

	
	void testEnterKey(boolean isNewGame, boolean isGameOver, boolean expectedReset) {
		try {
			var robot = new Robot();
			game.initGame();
			game.score = 1;
			game.isNewGame = isNewGame;
			game.isGameOver = isGameOver;
			
			robot.setAutoDelay(200);
			robot.keyPress(KeyEvent.VK_ENTER);
			
			robot.keyRelease(KeyEvent.VK_ENTER);
			game.repaint();
			assertEquals(expectedReset ? 0 : 1, game.score);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			fail();
		}
	}

	@Test
	void testEnterKeyNewGame() {
		testEnterKey(true, false, true);
	}
	
	@Test
	void testEnterKeyGameOver() {
		testEnterKey(false,true,true);
	}
	@Test
	void testEnterKeyNewGameAndGameOver() {
		testEnterKey(true,true,true);
	}
	
	@Test
	void testEnterKeyDoNothing() {
		testEnterKey(false,false, false);
	}
	
	void testPKey(boolean initPaused, boolean initGameOver, boolean expectedPaused) {
		try {
			var robot = new Robot();
			game.initGame();
			game.isPaused = initPaused;
			game.logicTimer.isPaused = initPaused;
			game.isNewGame = false;
			game.isGameOver = initGameOver;
			robot.setAutoDelay(200);
			robot.keyPress(KeyEvent.VK_P);
			
			robot.keyRelease(KeyEvent.VK_P);
			game.repaint();
			assertEquals(expectedPaused, game.isPaused, "game paused");
			assertEquals(expectedPaused, game.logicTimer.isPaused, "clock paused");
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			fail();
		}
	}
	
	@Test
	void testPKeyPausedGameOver() {
		testPKey(true, true, true);
	}
	@Test
	void testPKeyPausedGameNotOver() {
		testPKey(true, false, false);
	}
	
	@Test
	void testPKeyUnpausedGameOver() {
		testPKey(false, true, false);
	}
	@Test
	void testPKeyUnpausedGameNotOver() {
		testPKey(false, false, true);
	}


}
