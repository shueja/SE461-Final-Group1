package org.psnbtech.keys;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.psnbtech.Direction;
import org.psnbtech.SnakeGame;

public class AKeyTest extends MovementKeyTest{
	@BeforeEach
	void setDir() {
		dir = Direction.West;
		left = Direction.South;
		right = Direction.North;
		reverse = Direction.East;
		key = KeyEvent.VK_A;
	}
	
}
