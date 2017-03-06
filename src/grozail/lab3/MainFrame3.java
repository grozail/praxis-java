package grozail.lab3;

import grozail.lab3.clock.ClockPanel;
import grozail.lab3.diagram.DiagramPanel;
import grozail.lab3.spin.SpinPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by grozail
 * on 3.3.17.
 */
public class MainFrame3 extends JFrame {
	public static final Toolkit DEF_TOOLKIT = Toolkit.getDefaultToolkit();
	public static final int DEF_WIDTH = DEF_TOOLKIT.getScreenSize().width / 2;
	public static final int DEF_HEIGHT = DEF_TOOLKIT.getScreenSize().height / 2;
	public static final Dimension DEF_DIMMENSION = new Dimension(DEF_WIDTH, DEF_HEIGHT);
	private ClockPanel clockPanel = new ClockPanel();
	private SpinPanel spinPanel = new SpinPanel();
	private DiagramPanel diagramPanel = new DiagramPanel();
	private JTabbedPane tabbedPane = new JTabbedPane();

	public MainFrame3() {
		super("Lab 3");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(DEF_WIDTH, DEF_HEIGHT);
		setMinimumSize(DEF_DIMMENSION);
		setLocation(DEF_WIDTH, 0);
		setLayout(new BorderLayout());
		add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("Task3", diagramPanel);
		tabbedPane.addTab("Task1", clockPanel);
		tabbedPane.addTab("Task2", spinPanel);
		setVisible(true);
	}
}
