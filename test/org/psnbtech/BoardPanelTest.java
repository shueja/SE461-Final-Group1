package org.psnbtech;

import static org.junit.jupiter.api.Assertions.*;
import static org.psnbtech.BoardPanel.*;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardPanelTest {

	SnakeGame game;
	BoardPanel panel;
	@BeforeEach
	void setUp() {
		game = new SnakeGame();
		panel = new BoardPanel(game);
	}


	@Test
	void testPanelSize() {
		var size = panel.getPreferredSize();
		assertEquals(size.width, COL_COUNT * TILE_SIZE);
		assertEquals(size.height, ROW_COUNT * TILE_SIZE);
	}
	
	@Test
	void testColor() {
		assertEquals(panel.getBackground(), Color.BLACK);
	}
	
	@Test
	void testTilesInit() {
		assertEquals(panel.tiles.length, ROW_COUNT * COL_COUNT);
		for (TileType tile: panel.tiles) {
			assertNull(tile);
		}
	}
	@Test
	void testClearBoard() {
		for (int i = 0; i < panel.tiles.length; i++) {
			panel.tiles[i]=TileType.Fruit;
		}
		for (TileType tile: panel.tiles) {
			assertNotNull(tile);
		}
		panel.clearBoard();
		for (TileType tile: panel.tiles) {
			assertNull(tile);
		}
	}
	
	@Test
	void testSetTilePoint() {
		assertNull(panel.tiles[0]);
		panel.setTile(new Point(0,0), TileType.Fruit);
		assertEquals(panel.tiles[0], TileType.Fruit);
	}
	@Test
	void testSetTilePointYOOB() {
		try {
		panel.setTile(new Point(0,ROW_COUNT+1), TileType.Fruit);
		} catch (Exception e) {
			assertTrue(true); //JUnit doesn't have a succeed() method
			return;
		}
		fail("Did not throw IndexOutOfBoundsException");
	}
	@Test
	void testSetTilePointXOOB() {
		try {
		panel.setTile(new Point(COL_COUNT+1,0), TileType.Fruit);
		assertTrue(true); //JUnit doesn't have a succeed() method
		} catch (IndexOutOfBoundsException e) {
			fail("Threw IndexOutOfBoundsException"); 
		}
		
	}
	@Test
	void testSetTileFruit() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		panel.drawTile(0, 0, TileType.Fruit, image.getGraphics());
	}
	@Test
	void testSetTileSnakeBody() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		panel.drawTile(0, 0, TileType.SnakeBody, image.getGraphics());
		
	}
	@Test
	void testSetTileNorthHead() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		game.initGame();
		game.directions.add(Direction.North);
		panel.drawTile(0, 0, TileType.SnakeHead, image.getGraphics());
	}
	@Test
	void testSetTileSouthHead() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		game.initGame();
		game.directions.add(Direction.South);
		panel.drawTile(0, 0, TileType.SnakeHead, image.getGraphics());
	}
	@Test
	void testSetTileEastHead() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		game.initGame();
		game.directions.add(Direction.East);
		panel.drawTile(0, 0, TileType.SnakeHead, image.getGraphics());
	}
	@Test
	void testSetTileWestHead() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		game.initGame();
		game.directions.add(Direction.West);
		panel.drawTile(0, 0, TileType.SnakeHead, image.getGraphics());
	}

}
