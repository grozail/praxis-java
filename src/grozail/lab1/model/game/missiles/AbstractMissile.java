package grozail.lab1.model.game.missiles;

import grozail.lab1.model.game.Bounds;
import grozail.lab1.model.game.GameObject;
import grozail.lab1.model.game.helicopters.AIHelicopter;
import grozail.lab1.model.game.helicopters.states.States;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by grozail
 * on 12.2.17.
 */
public abstract class AbstractMissile extends GameObject {

	protected int speed = 6;
	protected BufferedImage missileBody;

	public AbstractMissile launch(int x, int y) {
		stopAnimation = false;
		this.x = x;
		this.y = y;
		animation = new Thread(this::animate);
		animation.start();
		return this;
	}

	@Override
	protected void animate() {
		while (!stopAnimation) {
			for (int i = 1; i < sprites.length; i++) {
				currentSprite = sprites[i];
				x += speed;
				updateObjectBounds();
				Bounds aiBounds = AIHelicopter.getInstance().getObjectBounds();
				int centerY = (objectBounds.top + objectBounds.bottom) / 2;
				if(objectBounds.right > aiBounds.left && centerY >= aiBounds.top && centerY <= aiBounds.bottom && objectBounds.left < aiBounds.left){
					AIHelicopter.getInstance().setState(States.FALLING);
				}
				if (mustBeDestroyed()) {
					stopAnimation = true;
					break;
				}
				try {
					Thread.sleep(60);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(missileBody, x, y, null);
		g.drawImage(currentSprite, x + getFlamePosition(), y, null);
	}

	protected abstract int getFlamePosition();

	public abstract boolean mustBeDestroyed();
}
