package org.psnbtech;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.psnbtech.SnakeGame;

class SnakeGameTest {

	SnakeGame game;
	@BeforeEach
	void setUp() throws Exception {
		game = new SnakeGame();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		game.initGame();
		game.resetGame();
		game.repaint();
		game.updateGame();
	}

}
