package grozail.lab5;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import java.util.Enumeration;

/**
 * Created by grozail
 * on 26.3.17.
 */
public class ExcelTable extends JTable {

	private ExcelTable(ExcelTableModel dm) {
		super(dm);
	}

	public static ExcelTable create() {
		ExcelTable table = new ExcelTable(new ExcelTableModel());
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		Enumeration<TableColumn> columns = table.getColumnModel().getColumns();
		while (columns.hasMoreElements()) {
			 TableColumn column = columns.nextElement();
			 column.setMinWidth(60);
		}
		return table;
	}


	private static class ExcelTableModel extends AbstractTableModel {

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return columnIndex != 0;
		}

		@Override
		public int getRowCount() {
			return MainFrame5.ROW_COUNT;
		}

		@Override
		public int getColumnCount() {
			return MainFrame5.COLUMN_COUNT;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return null;
		}
	}
}
