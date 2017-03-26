package grozail.lab4;

import javax.swing.*;
import java.awt.*;

/**
 * Created by grozail
 * on 13.3.17.
 */
public class MainFrame4 extends JFrame {

	public static final Toolkit DEF_TOOLKIT = Toolkit.getDefaultToolkit();
	public static final int DEF_WIDTH = DEF_TOOLKIT.getScreenSize().width / 2;
	public static final int DEF_HEIGHT = DEF_TOOLKIT.getScreenSize().height / 2;
	public static final Dimension DEF_DIMMENSION = new Dimension(DEF_WIDTH, DEF_HEIGHT);
	private JTabbedPane tabbedPane = new JTabbedPane();
	private CheckPanel checkPanel = new CheckPanel();
	private TextAnalyzerPanel textAnalyzerPanel = new TextAnalyzerPanel();
	public MainFrame4() {
		super("lab4");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(DEF_WIDTH, DEF_HEIGHT);
		setMinimumSize(DEF_DIMMENSION);
		setLocation(DEF_WIDTH, 0);
		setLayout(new BorderLayout());
		add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("Task 2",textAnalyzerPanel);
		tabbedPane.addTab("Task 1", checkPanel);
		setVisible(true);
	}
}
