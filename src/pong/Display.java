package pong;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display extends Canvas{

	private static final long serialVersionUID = 1L;
	public static final int width = 800, height = 600;
	public static String title = "Pong";
	
	private Canvas canvas;
	private JFrame frame;
	
	public Display() {
		this.drawDisplay();
	}
	
	public void update() {
		this.repaint();
	}

	private void drawDisplay() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		frame.add(canvas);
		frame.pack();
	}

	public JFrame getFrame() {
		return frame;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	

}
