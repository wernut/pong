package pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Implementing key listener to pick up the keys pressed and released
public class KeyManager implements KeyListener {

	// Creating a new array of booleans call keys.
	private boolean keys[];
	// Player input variables
	public boolean up, down, w, s, space;
	
	public KeyManager() {
		// Setting the keys array to a range of 256
		keys = new boolean[256];
	}
	
	public void update() {
		// Setting the input variables to booleans in the keys array
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		w = keys[KeyEvent.VK_W];
		s = keys[KeyEvent.VK_S];
		space = keys[KeyEvent.VK_SPACE];
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Adding the keycode that is pressed to the keys array and setting it to true
		// until the key is released.
		keys[e.getKeyCode()] = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

}
