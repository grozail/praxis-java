package grozail.lab1.view.mainform;

import grozail.lab1.controller.Controller;
import grozail.lab1.view.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by
 * grozail on 7.2.17.
 */
public class MainFrame1 extends JFrame {
	private Controller controller;
	public MainFrame1() {
		super("Helicopter battle");
		controller = Controller.getInstance();
		setWindowParameters();
		setClosingAction();

		installGameOnFrame();

		setVisible(true);

		controller.initializeGame();
		controller.startGame();
	}

	/* setting screen
	*   size
	*   minimum size
	*   default closing operation
	*   location
	*   layout
	* */
	private void setWindowParameters() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		setSize(screenWidth / 2, screenHeight / 2);
		setMinimumSize(new Dimension(600, 480));
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setLocation(screenWidth / 2, 0);
		setLayout(new BorderLayout());
	}
	/*
	* closing action mb set to instantly close
	* */
	private void setClosingAction() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);//todo remove after debug
				int action = JOptionPane.showConfirmDialog(MainFrame1.this, "Do you really want to exit?", "Confirm exit", JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
				setVisible(true);
			}
		});
	}

	private void installGameOnFrame() {
		add(Game.getInstance(), BorderLayout.CENTER);
	}
}
