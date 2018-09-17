package pong.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import pong.Display;
import pong.Pong;

public class MenuState extends State {

	public MenuState(Pong pong) {
		super(pong);
	}
	

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		if (pong.getKeyManager().space) {
			State.setState(pong.gameState);
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier", 1, 50));
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.drawString(Display.title, Display.width / 2 - g.getFontMetrics().stringWidth(Display.title) / 2,
				Display.height / 2 - 15);
		g.setFont(new Font("Courier", 1, 20));
		String info = "Press space for 2 player!";
		g.drawString(info, Display.width / 2 - g.getFontMetrics().stringWidth(info) / 2, Display.height / 2 + 15);
		String info2 = "Press enter to play with bot!";
		g.drawString(info2, Display.width / 2 - g.getFontMetrics().stringWidth(info2) / 2, Display.height / 2 + 45);
	}

}
