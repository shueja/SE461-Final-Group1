package org.psnbtech;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.lang.reflect.Constructor;

import java.awt.Point;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.psnbtech.SnakeGame;

class SnakeGameTest {

	SnakeGame game;
	Robot robot;
	@BeforeEach
	void setUp() {
		game = new SnakeGame();
		
	}

	@AfterEach
	void tearDown() {
	}
	
	void testEnterKey(boolean isNewGame, boolean isGameOver, boolean expectedReset) {
		try {
			var robot = new Robot();
			game.initGame();
			game.score = 1;
			game.isNewGame = isNewGame;
			game.isGameOver = isGameOver;
			
			robot.setAutoDelay(100);
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
			game.isGameOver = initGameOver;
			robot.setAutoDelay(100);
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

	void testUpdateGame(TileType collision, int expectedFruitsEaten, boolean expectedGameOver, boolean expectedPaused) {
		
		game.initGame();
		game.isNewGame = false;
		game.isGameOver = false;
		game.score = game.getScore();
		game.fruitsEaten = game.getFruitsEaten();
		game.nextFruitScore = game.getNextFruitScore();
		game.spawnFruit();
		
		//getting NullPointerException: Cannot read field "x" because "point" is null
		//testing random initialization value
		Point initialPosition = new Point(5, 5);
		game.snake.clear();
		game.snake.addFirst(initialPosition);
				
		//need to make sure not null
		game.directions.clear();
		game.directions.add(Direction.North);
		
		//prep board
		game.board.clearBoard();
		Point nextPosition = new Point(initialPosition.x, initialPosition.y - 1);
		game.board.setTile(nextPosition, collision);
		
		
		//Runs logic for collision
		game.updateGame();
			
		//Test the results of the collision
		assertEquals(expectedGameOver, game.isGameOver(), "Game over state mismatch.");
		//assertEquals(expectedScore, game.getScore(), "Score mismatch.");
		assertEquals(expectedFruitsEaten, game.getFruitsEaten(), "Fruits eaten mismatch.");
		assertEquals(expectedPaused, game.logicTimer.isPaused, "Game logicTimer paused mismatch.");		
	}
	
	@Test
	void testUpdateGameCollisionWithFruit() {
		testUpdateGame(TileType.Fruit, 1, false, false);//removed int expectedScore	
	}
	
	@Test
	void testUpdateGameCollisionWithSnakeBody() {
		testUpdateGame(TileType.SnakeBody, 0, true, true);//removed int expectedScore	
	}
	
	@Test
	void testUpdateGameNoCollision() {
		testUpdateGame(null, 0, false, false);//removed int expectedScore	
	}
}
