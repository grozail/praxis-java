package grozail.lab1.model.game.helicopters;

import grozail.lab1.model.game.missiles.AbstractMissile;
import grozail.lab1.model.game.missiles.PlayerMissile;

import java.util.LinkedList;

/**
 * Created by grozail
 * on 12.2.17.
 */
public class MissileController {
	final LinkedList<AbstractMissile> awailableMissiles = new LinkedList<>();
	final LinkedList<AbstractMissile> launchedMissiles = new LinkedList<>();
	private final MissileObserver missileObserver = new MissileObserver();
	private boolean disabled = false;

	private MissileController() {
		missileObserver.observer.start();
	}

	static MissileController createForPlayer() {
		MissileController missileController = new MissileController();
		for (int i = 0; i < 4; i++) {
			missileController.awailableMissiles.addLast(new PlayerMissile());
		}
		return missileController;
	}

	/*static MissileController createForAI() {
		MissileController missileController = new MissileController();
		for (int i = 0; i < 4; i++) {
			missileController.awailableMissiles.addLast(new AIMissile());
		}
		return missileController;
	}*/

	void launchMissile(int x, int y) {
		launchedMissiles.addLast(awailableMissiles.pop().launch(x, y));
		System.out.println("Missile launched");
	}

	void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	boolean isDisabled() {
		return disabled;
	}

	public LinkedList<AbstractMissile> getLaunchedMissiles() {
		return launchedMissiles;
	}

	public LinkedList<AbstractMissile> getAwailableMissiles() {
		return awailableMissiles;
	}

	private class MissileObserver {

		private Thread observer = new Thread(this::observe);

		private void observe() {
			while (!disabled) {
				for (AbstractMissile missile : launchedMissiles) {
					if (missile.mustBeDestroyed()) {
						awailableMissiles.addLast(missile);
						System.out.println("Missile caught " + missile.getId());
					}
				}
				for (AbstractMissile missile : awailableMissiles) {
					launchedMissiles.remove(missile);
				}
				try {
					Thread.sleep(100);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
