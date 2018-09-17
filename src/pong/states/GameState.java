package pong.states;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import pong.Ball;
import pong.Player;
import pong.Pong;

public class GameState extends State{
	

	public Player player1, player2;
	
	private Ball ball, ball2;
	
	private Pong pong;
	
	
	public GameState(Pong pong) {
		super(pong);
		this.pong = pong;
		this.init();
	}
	
	public void init() {
		player1 = new Player(pong, 1);
		player2 = new Player(pong, 2);
		ball = new Ball(pong);
		//ball2 = new Ball(pong);
	}

	@Override
	public void update() {
		input();
		ball.update(player1, player2);
		//ball2.update(player1, player2);
	}
	

	public void input() {
		if (pong.getKeyManager().w) {
			this.player1.move(true);
		}
		if (pong.getKeyManager().s) {
			this.player1.move(false);
		}
		if (pong.getKeyManager().up) {
			this.player2.move(true);
		}
		if (pong.getKeyManager().down) {
			this.player2.move(false);
		}
	}


	@Override
	public void render(Graphics g) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		player1.renderPlayer(g);
		player2.renderPlayer(g);
		ball.render(g);
		//ball2.render(g);
		g.setColor(Color.WHITE);
		((Graphics2D) g).setStroke(new BasicStroke(5f));
		g.drawLine(pong.getWidth() / 2, 0, pong.getWidth() / 2, pong.getHeight());
		g.drawOval(pong.getWidth() / 2 - 150, pong.getHeight() / 2 - 150, 300, 300);
		renderUI(g);
	}
	
	private void renderUI(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier", 1, 20));
		g.drawString("Player 1: " + player1.score, 6, 15);
		g.drawString("Player 2: " + player2.score, pong.getWidth() - g.getFontMetrics().stringWidth("Player2: ") - 45, 15);
	}

}
