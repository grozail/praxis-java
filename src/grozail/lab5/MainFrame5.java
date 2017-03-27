package grozail.lab5;

import javax.swing.*;
import java.awt.*;

/**
 * Created by grozail
 * on 26.3.17.
 */
public class MainFrame5 extends JFrame {
	public static final Toolkit DEF_TOOLKIT = Toolkit.getDefaultToolkit();
	public static final int DEF_WIDTH = DEF_TOOLKIT.getScreenSize().width / 2;
	public static final int DEF_HEIGHT = DEF_TOOLKIT.getScreenSize().height / 2;
	public static final Dimension DEF_DIMMENSION = new Dimension(DEF_WIDTH, DEF_HEIGHT);

	public static final int ROW_COUNT = 10;
	public static final int COLUMN_COUNT = 10;

	private ExcelTablePanel excelTablePanel;
	public MainFrame5() {
		super("fucking excel in swing");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(DEF_WIDTH, DEF_HEIGHT);
		setMinimumSize(DEF_DIMMENSION);
		setLocation(DEF_WIDTH, 0);
		setLayout(new BorderLayout());
		excelTablePanel = new ExcelTablePanel();
		add(excelTablePanel, BorderLayout.CENTER);
		pack();
		setVisible(true);

	}

	public static void log(String message) {
		System.out.println(message);
	}
}
