package grozail.lab1.model.game.helicopters;

import grozail.lab1.model.game.helicopters.states.Directions;
import grozail.lab1.view.game.Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by grozail
 * on 7.2.17.
 */
public class AIHelicopter extends AbstractHelicopter {
	private static AIHelicopter INSTANCE = new AIHelicopter();
	private static Random aiRandomizer = new Random();
	private static final int NUMBER_OF_ALLOWED_ACTIONS = 8;
	public static AIHelicopter getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new AIHelicopter();
		}
		return INSTANCE;
	}

	private AIHelicopter() {
		init();
	}

	@Override
	public void init() {
		setId();
		setAllowedArea(Game.getInstance().getWidth() / 2, 0, Game.getInstance().getWidth(), Game.getInstance().getHeight());
		x = (allowedArea.left + allowedArea.right) / 2 - 20;
		y = (allowedArea.top + allowedArea.bottom) / 2 - 10;
		sprites = new BufferedImage[2];
		loadAssets();
		updateObjectBounds();
	}

	/*DO BEFORE UPDATING BOUNDS*/
	@Override
	protected void loadAssets() {
		try {
			sprites[0] = ImageIO.read(new File(assetsPath + "ai/sprite_1_ai.png"));
			sprites[1] = ImageIO.read(new File(assetsPath + "ai/sprite_2_ai.png"));
			currentSprite = sprites[0];
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void simulateActions() {
		int action = Math.abs(aiRandomizer.nextInt() % NUMBER_OF_ALLOWED_ACTIONS);
		switch (action) {
			case 0:
				setDirection(Directions.UP);
				break;
			case 1:
				setDirection(Directions.DOWN);
				break;
			case 2:
				setDirection(Directions.LEFT);
				break;
			case 3:
				setDirection(Directions.RIGHT);
				break;
			case 4:
				setDirection(Directions.UP_LEFT);
				break;
			case 5:
				setDirection(Directions.DOWN_LEFT);
				break;
			case 6:
				setDirection(Directions.UP_RIGHT);
				break;
			case 7:
				setDirection(Directions.DOWN_RIGHT);
				break;
		}
	}

	@Override
	protected int getXForLaunch() {
		return objectBounds.left;
	}

	@Override
	protected int getYForLaunch() {
		return (objectBounds.top + objectBounds.bottom) / 2;
	}
}
