package ticket.ssq;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.zchaos.zutil.datagrid.DataGrid;
import com.zchaos.zutil.datagrid.DataGridCell;
import com.zchaos.zutil.datagrid.DataGridHead;
import com.zchaos.zutil.datagrid.DataGridHeadCell;
import com.zchaos.zutil.datagrid.impl.DataGridCellImpl;
import com.zchaos.zutil.datagrid.impl.DataGridHeadCellImpl;
import com.zchaos.zutil.datagrid.impl.DataGridHeadImpl;
import com.zchaos.zutil.datagrid.impl.DataGridImpl;

/**
 * 双色球篮球分析
 * @author zhuchx
 */
public class SSQBlueAnalyse {
	public static void main(String[] args) {
		SSQBlueAnalyse analyse = new SSQBlueAnalyse();
		analyse.start();
	}

	public void start() {
//		DataGrid data = getData();

		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 500, 500);

		//		TableColumnModel columnTitle = createColumnModel(data);
		//		TableModel tableData = createTableModel(data);
		Object[] columns = new Object[] { "A", "B" };
		Object[][] datas = new Object[][] { { "0-0", "0-1" }, { "1-0", "1-1" } };

		//以二维数组和一维数组来创建一个JTable对象  
		JTable table = new JTable(datas, columns);
		//将JTable对象放在JScrollPane中，并将该JScrollPane放在窗口中显示出来  
		frame.add(new JScrollPane(table));

		frame.setVisible(true);
	}

	private TableColumnModel createColumnModel(DataGrid data) {
		TableColumnModel columnTitle = new DefaultTableColumnModel();

		DataGridHead head = data.getTopHead();
		if (head != null) {
			int size = head.size();
			for (int i = 0; i < size; i++) {
				DataGridHeadCell cell = head.getCell(i);

				TableColumn column = new TableColumn();
				column.setHeaderValue(cell.getValue());
				columnTitle.addColumn(column);
			}
		}

		return columnTitle;
	}

	private TableModel createTableModel(DataGrid data) {
		int rowcount = data.getRowCount();
		int colcount = data.getColCount();

		TableModel tableData = new DefaultTableModel(rowcount, colcount);
		for (int i = 0; i < rowcount; i++) {
			for (int j = 0; j < colcount; j++) {
				DataGridCell cell = data.getCell(i, j);

				tableData.setValueAt(cell.getValue(), i, j);
			}
		}

		return tableData;
	}

	public DataGrid getData() {
		//		return SSQTicketExcelReader.getSSQData(2012);
		int rowcount = 2;
		int colcount = 2;

		DataGridImpl dataGrid = new DataGridImpl(rowcount, colcount);
		int col = 0;
		DataGridHeadImpl head = new DataGridHeadImpl(colcount);
		head.addCell(col++, new DataGridHeadCellImpl("A"));
		head.addCell(col++, new DataGridHeadCellImpl("B"));
		dataGrid.setTopHead(head);

		for (int i = 0; i < rowcount; i++) {
			for (int j = 0; j < colcount; j++) {
				DataGridCellImpl cell = new DataGridCellImpl(i + "0" + j);
				dataGrid.addCell(i, j, cell);
			}
		}

		return dataGrid;

	}
}
