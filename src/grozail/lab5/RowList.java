package grozail.lab5;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * Created by grozail
 * on 27.3.17.
 */
public class RowList extends JList {

	public RowList(ListModel dataModel) {
		super(dataModel);
	}

	public static RowList create(JTable table) {
		ListModel model = new AbstractListModel() {
			@Override
			public int getSize() {
				return MainFrame5.ROW_COUNT;
			}

			@Override
			public Object getElementAt(int index) {
				return index;
			}
		};
		RowList list = new RowList(model);
		list.setFixedCellWidth(String.valueOf(MainFrame5.ROW_COUNT).length() * 10);
		list.setFixedCellHeight(table.getRowHeight());
		list.setCellRenderer(new RowHeaderRenderer(table));
		return list;
	}


	private static class RowHeaderRenderer extends JLabel implements ListCellRenderer {

		RowHeaderRenderer(JTable table) {
			JTableHeader header = table.getTableHeader();
			setOpaque(true);
			setBorder(UIManager.getBorder("TableHeader.cellBorder"));
			setHorizontalAlignment(CENTER);
			setForeground(header.getForeground());
			setBackground(header.getBackground());
			setFont(header.getFont());
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			setText((value == null) ? "" : value.toString());
			return this;
		}
	}
}
