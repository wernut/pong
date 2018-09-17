package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
	
	// Various variables for the player, such as location, size, ID and score.
	public int playerID, x, y, width = 30, height = 170, score;

	// Player move speed
	public float moveSpeed = 10.5f;

	public Player(Pong pong, int playerid) {
		// If playerID is 1, then render on the left, if playerID is 2, then render on
		// the right.
		switch (playerid) {
		case 1:
			// if 1
			this.playerID = 1;
			this.x = 0;
			break;
		case 2:
			// if 2
			this.playerID = 2;
			this.x = pong.getWidth() - this.width;
			break;
		}
		// Rendering each player in the middle of the Y axis.
		this.y = pong.getHeight() / 2 - this.height / 2;
	}

	public void update() {
	}

	public void renderPlayer(Graphics g) {
		// Setting player color to white and drawing the players as a rectangle.
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}

	// Player collision
	// This determines if the player can move up or down by checking if the
	// rectangle collides with the edge of the frame.
	public void move(boolean up) {
		if (up && this.y >= -1) {
			this.y -= moveSpeed;
		} else if (this.y <= Display.height - this.height) {
			this.y += moveSpeed;
		}
	}
}
