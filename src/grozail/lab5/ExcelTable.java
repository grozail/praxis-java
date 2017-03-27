package grozail.lab5;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
/**
 * Created by grozail
 * on 26.3.17.
 */
public class ExcelTable extends JTable {

	public ExcelTable(ExcelTableModel dm) {
		super(dm);
	}

	public static ExcelTable create() {
		return new ExcelTable(new ExcelTableModel());
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
