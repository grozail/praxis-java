package grozail.app;

import grozail.lab1.view.mainform.MainFrame1;

import javax.swing.*;

/**
 * Created by
 * grozail on 7.2.17.
 */
public class Application {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(MainFrame1::new);
	}
}
