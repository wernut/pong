package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import pong.states.GameState;
import pong.states.MenuState;
import pong.states.State;

public class Pong implements Runnable {

	private boolean running = false;

	private Graphics g;

	private Thread thread;

	private BufferStrategy bs;

	private Display display;

	public Player player1, player2;

	private KeyManager keyManager;
	
	public State gameState;

	public State menuState;
	
	private int ticks = 0;

	public Pong() {
		this.start();
	}

	public static void main(String[] args0) {
		Pong pong = new Pong();
	}

	public void init() {
		display = new Display();
		keyManager = new KeyManager();
		display.getFrame().addKeyListener(keyManager);
		gameState = new GameState(this);
		menuState = new MenuState(this);
		State.setState(menuState);
	}

	@Override
	public void run() {
		init();
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				update();
				render();
				ticks++;
				delta--;
			}

			if (timer >= 1000000000) {
				System.out.println("FPS: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}

		stop();

	}
	public void update() {
		keyManager.update();
		if(State.getState() != null) {
			State.getState().update();
		}
		display.update();
	}

	public void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, Display.width, Display.height);
		// Making the background black
		g.setColor(Color.black);
		g.fillRect(0, 0, Display.width, Display.height);
		g.setColor(Color.white);
		// Render game state
		if(State.getState() != null) {
			State.getState().render(g);
		}
		bs.show();
		g.dispose();
	}

	private void renderPaused(Graphics g) {
		g.setFont(new Font("Courier", 1, 20));
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		String title = "Paused";
		g.drawString(title, Display.width / 2 - g.getFontMetrics().stringWidth(title) / 2, Display.height / 2 - 15);
		String info = "Press space to resume!";
		g.drawString(info, Display.width / 2 - g.getFontMetrics().stringWidth(info) / 2, Display.height / 2 + 15);
	}
	
	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getWidth() {
		return display.width;
	}

	public int getHeight() {
		return display.height;
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public String getFPS() {
		return Integer.toString(ticks);
	}

}
