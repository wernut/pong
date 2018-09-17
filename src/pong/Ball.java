package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {

	// Ball's location and size variables
	public int x, y, width = 25, height = 25;

	// Move X or Y
	public int motionX, motionY;

	// Calling the Random class
	public Random random;

	// Hit counter
	public int amountOfHits;

	private Pong pong;

	public Ball(Pong pong) {
		random = new Random();
		this.pong = pong;
		spawnBall();
	}

	public void spawnBall() {
		// Setting the amount of hits to 0 each spawn
		this.amountOfHits = 0;
		// Setting the ball's location to the middle of the screen
		this.x = pong.getWidth() / 2 - this.width / 2;
		this.y = pong.getHeight() / 2 - this.height / 2;
		// On each spawn, motionY has a random range of -2 to 4
		this.motionY = -2 + random.nextInt(4);
		// If motionY equals 0, then make it one
		if (motionY == 0) {
			motionY = 1;
		}

		if (random.nextBoolean()) {
			motionX = 1;
		} else {
			motionX = -1;
		}

	}

	public void update(Player player1, Player player2) {

		int speed = 5;

		this.x += motionX * speed;
		this.y += motionY * speed;

		if (this.y + height - motionY > pong.getHeight() || this.y + motionY < 0) {
			if (this.motionY < 0) {
				this.y = 0;
				this.motionY = random.nextInt(4);

				if (motionY == 0) {
					motionY = 1;
				}
			} else {
				this.motionY = -random.nextInt(4);
				this.y = pong.getHeight() - height;

				if (motionY == 0) {
					motionY = -1;
				}
			}
		}

		if (checkCollision(player1) == 1) {
			this.motionX = 1 + (amountOfHits / 5);
			this.motionY = -2 + random.nextInt(4);

			if (motionY == 0) {
				motionY = 1;
			}

			amountOfHits++;
		} else if (checkCollision(player2) == 1) {
			this.motionX = -1 - (amountOfHits / 5);
			this.motionY = -2 + random.nextInt(4);

			if (motionY == 0) {
				motionY = 1;
			}

			amountOfHits++;
		}

		if (checkCollision(player1) == 2) {
			player2.score++;
			spawnBall();
		} else if (checkCollision(player2) == 2) {
			player1.score++;
			spawnBall();
		}
	}

	public void render(Graphics g) {
		// Rendering the oval to the location and size of set variables, and setting the
		// color to white.
		g.setColor(Color.white);
		g.fillOval(x, y, width, height);
	}

	// Checks if the ball's location hits the players location
	public int checkCollision(Player player) {
		if (this.x < player.x + player.width && this.x + width > player.x && this.y < player.y + player.height
				&& this.y + height > player.y) {
			return 1; // Hits the player
		} else if ((player.x > x + width && player.playerID == 1) || (player.x < x && player.playerID == 2)) {
			return 2; // Hits behind the player
		}
		return 0; // Nothing
	}

}
