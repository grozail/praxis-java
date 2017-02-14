package grozail.lab1.view.game;

import grozail.lab1.model.game.GameObject;
import grozail.lab1.model.game.helicopters.AIHelicopter;
import grozail.lab1.model.game.helicopters.PlayableHelicopter;
import grozail.lab1.model.game.helicopters.states.Directions;
import grozail.lab1.model.game.missiles.PlayerMissile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * Created by grozail
 * on 9.2.17.
 */
public class Game extends JComponent {
	/*Singleton*/
	private static Game INSTANCE = new Game();

	public static Game getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Game();
		}
		return INSTANCE;
	}

	/*objects section*/
	private List<GameObject> objects = new ArrayList<>();
	private PlayableHelicopter player;
	private AIHelicopter aiHelicopter;

	/*threads section*/
	Thread thread = new Thread(this::startAnimations);
	/*utility section*/
	private Color colorBackground = new Color(135, 128, 119);
	private boolean stopAnimations = false;

	//test section
	private PlayerMissile testMissile;
	//------------
	/*Constructor*/
	private Game() {
		addKeyListener(new MultipleKeyListener());
		setFocusable(true);
		requestFocusInWindow();
		setDoubleBuffered(true);
	}

	public void initializeObjects() {
		player = PlayableHelicopter.getInstance();
		aiHelicopter = AIHelicopter.getInstance();
	}

	public void startGame() {
		objects.add(player);
		objects.add(aiHelicopter);
		objects.addAll(player.getMissileController().getAwailableMissiles());
		for (GameObject object : objects) {
			object.getAnimation().start();
		}
		thread.start();
	}

	private void startAnimations() {
		while (!stopAnimations) {
			aiHelicopter.simulateActions();
			repaint();
			try {
				Thread.sleep(30L);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics2D = ((Graphics2D) g);
		graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		graphics2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		graphics2D.setColor(colorBackground);
		graphics2D.fillRect(0, 0, getWidth(), getHeight());
		for (GameObject object : objects) {
			if (!object.animationStopped()) {
				object.draw(graphics2D);
			}

		}
		graphics2D.dispose();
	}

	private class MultipleKeyListener extends KeyAdapter {
		private final Set<Integer> pressedKeys = new HashSet<>();
		@Override
		public void keyPressed(KeyEvent e) {
			int pressed = e.getKeyCode();
			if (pressed == KeyEvent.VK_UP || pressed == KeyEvent.VK_DOWN || pressed == KeyEvent.VK_LEFT || pressed == KeyEvent.VK_RIGHT || pressed == KeyEvent.VK_SPACE) {
				pressedKeys.add(pressed);
				if (pressedKeys.size() > 1) {
					int mask = 0;
					for (Integer key : pressedKeys) {
						mask ^= key;
					}
					switch (mask) {
						case 1:
							player.setDirection(Directions.UP_RIGHT);
							break;
						case 3:
							player.setDirection(Directions.UP_LEFT);
							break;
						case 15:
							player.setDirection(Directions.DOWN_RIGHT);
							break;
						case 13:
							player.setDirection(Directions.DOWN_LEFT);
							break;
						case 33:
							player.setDirection(Directions.UP_RIGHT);
							player.fireMissile();
							break;
						case 35:
							player.setDirection(Directions.UP_LEFT);
							player.fireMissile();
							break;
						case 47:
							player.setDirection(Directions.DOWN_RIGHT);
							player.fireMissile();
							break;
						case 45:
							player.setDirection(Directions.DOWN_LEFT);
							player.fireMissile();
							break;

					}
				}
				else {
					switch (pressed) {
						case KeyEvent.VK_UP:
							player.setDirection(Directions.UP);
							break;
						case KeyEvent.VK_DOWN:
							player.setDirection(Directions.DOWN);
							break;
						case KeyEvent.VK_LEFT:
							player.setDirection(Directions.LEFT);
							break;
						case KeyEvent.VK_RIGHT:
							player.setDirection(Directions.RIGHT);
							break;
						case KeyEvent.VK_SPACE:
							player.fireMissile();
							break;
					}
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int released = e.getKeyCode();
			if (released == KeyEvent.VK_UP || released == KeyEvent.VK_DOWN || released == KeyEvent.VK_LEFT || released == KeyEvent.VK_RIGHT || released == KeyEvent.VK_SPACE) {
				pressedKeys.remove(released);
			}
		}
	}

}
