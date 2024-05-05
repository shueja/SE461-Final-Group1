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

public class SnakeGameTest {

	SnakeGame game;
	Robot robot;
	@BeforeEach
	void setUp() {
		game = new SnakeGame();
		
	}

	

	void testUpdateGame(TileType collision, int expectedFruitsEaten, int expectedFruitScore, boolean expectedGameOver, boolean expectedPaused) {
		
		game.initGame();
		game.resetGame();
		
		//getting NullPointerException: Cannot read field "x" because "point" is null
		//testing random initialization value
		Point initialPosition = game.snake.peekFirst();
		
		//prep board
		Point nextPosition = new Point(initialPosition.x, initialPosition.y - 1);
		game.board.setTile(nextPosition, collision);
		
		
		//Runs logic for collision
		game.updateGame();
			
		//Test the results of the collision
		assertEquals(expectedGameOver, game.isGameOver(), "Game over state mismatch.");
		//assertEquals(expectedScore, game.getScore(), "Score mismatch.");
		assertEquals(expectedFruitsEaten, game.getFruitsEaten(), "Fruits eaten mismatch.");
		assertEquals(expectedPaused, game.logicTimer.isPaused, "Game logicTimer paused mismatch.");
		assertTrue(expectedFruitScore == game.nextFruitScore , "Next fruit score mismatch.");
	}
	
	@Test
	void testUpdateGameCollisionWithFruit() {
		testUpdateGame(TileType.Fruit, 1, 100, false, false);	
	}
	
	@Test
	void testUpdateGameCollisionWithSnakeBody() {
		testUpdateGame(TileType.SnakeBody, 0, 100, true, true);	
	}
	
	@Test
	void testUpdateGameNoCollision() {
		testUpdateGame(null, 0, 99, false, false);	
	}
}
