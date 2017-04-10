package grozail.lab5;

import javax.swing.*;
import java.awt.*;

/**
 * Created by grozail
 * on 26.3.17.
 */
public class ExcelTablePanel extends JPanel {
	private ExcelTable table;
	private RowList rowHeaderList;
	private TopPanel topPanel;
	public ExcelTablePanel() {
		setLayout(new BorderLayout());
		table = ExcelTable.create();

		rowHeaderList = RowList.create(table);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setRowHeaderView(rowHeaderList);
		add(scroll, BorderLayout.CENTER);
		topPanel = new TopPanel();
		add(topPanel, BorderLayout.NORTH);
	}

	private static class TopPanel extends JPanel{
		JLabel label;
		TextField textField;

		TopPanel() {
			label = new JLabel("=");
			textField = new TextField(100);
			add(label);
			add(textField);
		}
	}

}
