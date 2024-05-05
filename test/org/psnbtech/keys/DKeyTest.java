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

public class DKeyTest extends MovementKeyTest{
	@BeforeEach
	void setDir() {
		dir = Direction.East;
		left = Direction.North;
		right = Direction.South;
		reverse = Direction.West;
		key = KeyEvent.VK_D;
	}
	
}
