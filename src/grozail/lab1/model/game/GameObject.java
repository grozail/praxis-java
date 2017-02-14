package grozail.lab1.model.game;


import grozail.lab1.view.game.Game;

import java.awt.image.BufferedImage;

/**
 * Created by grozail
 * on 9.2.17.
 */
public abstract class GameObject implements Drawable {

	/*Coordinates and Bounds*/
	protected int x = 0;
	protected int y = 0;
	protected Bounds objectBounds = new Bounds(0, 0, 0, 0);
	protected Bounds allowedArea = new Bounds(0, 0, Game.getInstance().getWidth(), Game.getInstance().getHeight());

	/*Sprites*/
	protected BufferedImage[] sprites;
	protected BufferedImage currentSprite;

	/*Animation*/
	protected boolean stopAnimation = false;
	protected Thread animation = new Thread(this::animate);

	/*Utility*/
	protected String assetsPath = "src/grozail/lab1/assets/";
	private static int idCounter = 0;
	protected int id;

	protected boolean isInAllowedArea() {
		return objectBounds.left > allowedArea.left && objectBounds.right  < allowedArea.right &&
				objectBounds.top > allowedArea.top && objectBounds.bottom < allowedArea.bottom;
	}

	protected void updateObjectBounds() {
		objectBounds.left = x;
		objectBounds.top = y;
		objectBounds.right = x + currentSprite.getWidth();
		objectBounds.bottom = y + currentSprite.getHeight();
	}

	protected void setAllowedArea(int left, int top, int right, int bottom) {
		allowedArea.left = left;
		allowedArea.top = top;
		allowedArea.right = right;
		allowedArea.bottom = bottom;
	}

	/*DO BEFORE UPDATING BOUNDS*/
	protected abstract void loadAssets();

	protected abstract void init();

	protected abstract void animate();

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public BufferedImage getCurrentSprite() {
		return currentSprite;
	}

	public void setCurrentSprite(BufferedImage currentSprite) {
		this.currentSprite = currentSprite;
	}

	public BufferedImage[] getSprites() {
		return sprites;
	}

	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
	}

	public Thread getAnimation() {
		return animation;
	}

	public boolean animationStopped() {
		return stopAnimation;
	}

	public void setStopAnimation(boolean stopAnimation) {
		this.stopAnimation = stopAnimation;
	}

	public int getId() {
		return id;
	}

	protected void setId() {
		this.id = idCounter++;
	}

	public Bounds getObjectBounds() {
		return objectBounds;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GameObject) {
			return id == ((GameObject) obj).id;
		}
		return false;
	}
}
