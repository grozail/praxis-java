package grozail.lab1.controller;

import grozail.lab1.view.game.Game;

/**
 * Created by
 * grozail on 7.2.17.
 */
public class Controller {
	private static Controller INSTANCE = new Controller();

	public static Controller getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Controller();
		}
		return INSTANCE;
	}

	private Game game;
	private Controller() {
		game = Game.getInstance();
	}

	public void initializeGame() {
		game.initializeObjects();
	}

	public void startGame() {
		game.startGame();
	}


}
