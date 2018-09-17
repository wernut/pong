package pong.states;

import java.awt.Graphics;

import pong.Pong;

public abstract class State {

	private static State currentState = null;
	
	protected Pong pong;

	public static void setState(State state) {
		State.currentState = state;
	}

	public static State getState() {
		return currentState;
	}
	
	public State(Pong pong) {
		this.pong = pong;
	}
	
	public abstract void init();

	public abstract void update();

	public abstract void render(Graphics g);

}
