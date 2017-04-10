package grozail.lab5;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;

/**
 * Created by grozail
 * on 26.3.17.
 */
public class ExcelTable extends JTable {

	private static class ExcelTableModel extends AbstractTableModel {
		Object[][] data = new Object[MainFrame5.ROW_COUNT][MainFrame5.COLUMN_COUNT];
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return true;
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
			return data[rowIndex][columnIndex];
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			data[rowIndex][columnIndex] = aValue;
			fireTableCellUpdated(rowIndex, columnIndex);
		}
	}

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
		GregorianCalendar calendar = new GregorianCalendar(1980, 11, 12);
		System.out.println(calendar.getTime());
		calendar.add(Calendar.MONTH, 10);
		System.out.println(calendar.getTime());

		return table;
	}



	@Override
	public void editingStopped(ChangeEvent e) {
		super.editingStopped(e);
	}
}
