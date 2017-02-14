package grozail.lab1.model.game.missiles;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by grozail
 * on 12.2.17.
 */
public class PlayerMissile extends AbstractMissile {

	public PlayerMissile() {
		init();
	}

	@Override
	protected void init() {
		setId();
		stopAnimation = true;
		allowedArea.right += 40;
		speed = 8;
		loadAssets();
	}

	@Override
	protected void loadAssets() {
		sprites = new BufferedImage[5];
		try {
			sprites[0] = ImageIO.read(new File(assetsPath + "player/player_missile.png"));
			sprites[1] = ImageIO.read(new File(assetsPath + "player/player_missile_flame_1.png"));
			sprites[2] = ImageIO.read(new File(assetsPath + "player/player_missile_flame_2.png"));
			sprites[3] = ImageIO.read(new File(assetsPath + "player/player_missile_flame_3.png"));
			sprites[4] = ImageIO.read(new File(assetsPath + "player/player_missile_flame_4.png"));
			missileBody = sprites[0];
			currentSprite = sprites[1];
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected int getFlamePosition() {
		return -10;
	}

	@Override
	public boolean mustBeDestroyed() {
		return x > allowedArea.right;
	}
}
