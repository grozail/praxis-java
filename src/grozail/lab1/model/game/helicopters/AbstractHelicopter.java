package grozail.lab1.model.game.helicopters;

import grozail.lab1.model.game.GameObject;
import grozail.lab1.model.game.helicopters.states.Directions;
import grozail.lab1.model.game.helicopters.states.States;
import grozail.lab1.model.game.missiles.AbstractMissile;

import java.awt.*;

/**
 * Created by
 * grozail on 7.2.17.
 */
public abstract class AbstractHelicopter extends GameObject {
	protected States state = States.FLYING;
	protected Directions direction = Directions.STOP;
	protected int speed = 5;
	protected int debug = 0;
	protected MissileController missileController;

	public States getState() {
		return state;
	}

	public void setState(States state) {
		this.state = state;
	}

	public Directions getDirection() {
		return direction;
	}

	public void setDirection(Directions direction) {
		this.direction = direction;
	}

	@Override
	public void animate() {
		while (!stopAnimation) {
			if (state == States.FLYING) {
				for (int i = 0; i < sprites.length; i++) {
					currentSprite = sprites[i];
					int xBeforeMove = x;
					int yBeforeMove = y;

					handleDirection();

					updateObjectBounds();
					if (!isInAllowedArea()) {
						x = xBeforeMove;
						y = yBeforeMove;
						updateObjectBounds();
					}
					try {
						Thread.sleep(35L);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			else {
				updateObjectBounds();
				if (isInAllowedArea()) {
					y+=speed;
				}
				try {
						Thread.sleep(35L);
					}
					catch (InterruptedException e) {
						e.printStackTrace();

				}
			}
		}
	}

	private void handleDirection() {
		switch (direction) {
			case STOP:
				break;
			case UP:
				y-=speed;
				break;
			case DOWN:
				y+=speed;
				break;
			case LEFT:
				x-=speed;
				break;
			case RIGHT:
				x+=speed;
				break;
			case UP_LEFT:
				y-=speed;
				x-=speed;
				break;
			case UP_RIGHT:
				y-=speed;
				x+=speed;
				break;
			case DOWN_LEFT:
				y+=speed;
				x-=speed;
				break;
			case DOWN_RIGHT:
				y+=speed;
				x+=speed;
				break;
		}
		direction = Directions.STOP;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(currentSprite, x, y, null);
	}

	public void fireMissile() {
		missileController.launchMissile(getXForLaunch(), getYForLaunch());
	}

	protected abstract int getXForLaunch();

	protected abstract int getYForLaunch();

	public MissileController getMissileController() {
		return missileController;
	}


}
