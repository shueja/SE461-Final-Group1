package org.psnbtech;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.lang.reflect.Constructor;
import java.util.LinkedList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.psnbtech.SnakeGame;

abstract class UpdateSnakeTest {

	SnakeGame game;
	Robot robot;
	Direction dir;
	@BeforeEach
	void setUp() {
		game = new SnakeGame();
		
	}

	
	void testUpdateSnakeHitsWall() {
		game.initGame();
		game.directions.clear();
		game.directions.push(dir);
			game.snake.clear();
			switch (dir) {
			case North:
			case West:
				game.snake.push(new Point(0,0));
				break;
			case South:
			case East:
				default:
				game.snake.push(new Point(BoardPanel.COL_COUNT-1, BoardPanel.ROW_COUNT-1));
				
			}
			TileType result = game.updateSnake();
			assertEquals(TileType.SnakeBody, result, dir.toString());
	}



	void testUpdateSnake(boolean longEnough, TileType hits, boolean directionSize) {
		game.initGame();
		game.resetGame();
		game.directions.clear();
		game.directions.push(dir);
		Point tail = new Point(game.snake.peekFirst());
		Point head = new Point(game.snake.peekFirst());
		Point next = new Point(game.snake.peekFirst());
		switch(dir) {// Node 1
		case North:
			next.y--; //2
			break;
			
		case South:
			next.y++;//3
			break;
			
		case West:
			next.x--;//4
			break;
			
		case East:
			next.x++;//5
			break;
		}
		if (longEnough) {
			Point iter = new Point(game.snake.peekFirst());
			for (int i = 0; i < SnakeGame.MIN_SNAKE_LENGTH; i++) {
				switch(dir) {// Node 1
				// expand the snake in the direction opposite the movement direction
				case North:
					iter.y++; //2
					break;
					
				case South:
					iter.y--;//3
					break;
					
				case West:
					iter.x++;//4
					break;
					
				case East:
					iter.x--;//5
					break;
				}
				game.board.setTile(iter, TileType.SnakeBody);
				game.snake.addLast(iter);
			}
			tail = iter;
		}
		
		game.board.setTile(next, hits);
		int expectedDirSize = game.directions.size();
		if (directionSize) {
			game.directions.addLast(dir);
			// We expect an additional poll to remove what we just added
		}
		var result = game.updateSnake();
		game.repaint();
		assertEquals(hits, result);
		assertEquals(expectedDirSize, game.directions.size(), "dirSize");
		int expectedLength = longEnough ? SnakeGame.MIN_SNAKE_LENGTH + 1 : 2;
		if (longEnough && hits == TileType.Fruit) {
			expectedLength++;
		}
		if (hits != TileType.SnakeBody) {
		assertEquals(expectedLength, game.snake.size(), "length");
		}
	}
	
	/**
	 * 1. (1, n, 6, 7, 14)
	 */
	@Test
	void testHitsWall() {
		testUpdateSnakeHitsWall();
	}
	/**
	 * 2. (1, n, 6, 8, 10, 13, 14)
	 */
	@Test
	void testShortHitsBody() {
		testUpdateSnake(false, TileType.SnakeBody, false);
	}
	/**
	 * 3. (1, n, 6, 8, 9, 10, 13, 14) 
	 */
	@Test
	void testLongHitsBody() {
		testUpdateSnake(true, TileType.SnakeBody, false);
	}
	/**
	 * 4. (1, n, 6, 8, 10, 11, 13, 14) 
	 */
	@Test
	void testLongHitsFruit() {
		testUpdateSnake(true, TileType.Fruit, false);
	}
	
	/**
	 * 5. (1, n, 6, 8, 9, 10, 11, 13, 14)
	 */
	@Test
	void testLongHitsNone() {
		testUpdateSnake(true, null, false);;
	}
//	/**
//	 * (1, n, 6, 8, 10, 11, 13, 14) 
//	 */
//	@Test
//	void testShortHitsFruit() {
//		testUpdateSnake(false, TileType.Fruit, false);
//	}
//	/**
//	 * (1, n, 6, 8, 10, 11, 13, 14) 
//	 */
//	@Test
//	void testShortHitsNone() {
//		testUpdateSnake(false, null, false);
//	}


	/**
	 * 6. (1, n, 6, 8, 10, 11, 12, 13, 14) 
	 */
	@Test
	void testLongHitsFruitDir() {
		testUpdateSnake(true, TileType.Fruit, true);

	}
	/**
	 * 7. (1, n, 6, 8, 9, 10, 11, 12, 13, 14)
	 */
	@Test
	void testLongHitsNoneDir() {
		testUpdateSnake(true, null, true);

	}
	


}
