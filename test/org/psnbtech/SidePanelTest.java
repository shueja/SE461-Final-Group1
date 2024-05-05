package org.psnbtech;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.lang.reflect.Constructor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.psnbtech.SnakeGame;

class SidePanelTest {

	SnakeGame game;
	@BeforeEach
	void setUp() throws Exception {
		game = new SnakeGame();
	}


	@Test
	void testPanelSize() {
		var panel = new SidePanel(game);
		var size = panel.getPreferredSize();
		assertEquals(size.width, 300);
		assertEquals(size.height, BoardPanel.ROW_COUNT * BoardPanel.TILE_SIZE);
	}
	
	@Test
	void testColor() {
		var panel = new SidePanel(game);
		assertEquals(panel.getBackground(), Color.BLACK);
	}

}
