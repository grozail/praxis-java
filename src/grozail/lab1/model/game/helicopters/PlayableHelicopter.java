package grozail.lab1.model.game.helicopters;

import grozail.lab1.view.game.Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by grozail
 * on 7.2.17.
 */
public class PlayableHelicopter extends AbstractHelicopter {
	private static PlayableHelicopter INSTANCE = new PlayableHelicopter();

	public static PlayableHelicopter getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PlayableHelicopter();
		}
		return INSTANCE;
	}

	private PlayableHelicopter() {
		init();
	}

	@Override
	protected void init() {
		setId();
		speed = 10;
		setAllowedArea(0, 0, Game.getInstance().getWidth() / 2, Game.getInstance().getHeight());
		x = (allowedArea.left + allowedArea.right) / 2 - 20;
		y = (allowedArea.top + allowedArea.bottom) / 2 - 10;
		loadAssets();
		missileController = MissileController.createForPlayer();
		updateObjectBounds();
	}

	/*DO ONCE BEFORE UPDATING BOUNDS*/
	@Override
	protected void loadAssets() {
		sprites = new BufferedImage[2];
		try {
			sprites[0] = ImageIO.read(new File(assetsPath + "player/sprite_1_player.png"));
			sprites[1] = ImageIO.read(new File(assetsPath + "player/sprite_2_player.png"));
			currentSprite = sprites[0];
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected int getXForLaunch() {
		return objectBounds.right;
	}

	@Override
	protected int getYForLaunch() {
		return (objectBounds.top + objectBounds.bottom) / 2;
	}


}
