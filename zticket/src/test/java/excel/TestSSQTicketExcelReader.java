package excel;

import java.io.IOException;

import com.zchaos.zutil.datagrid.DataGrid;
import com.zchaos.zutil.datagrid.util.DataGridUtil;

public class TestSSQTicketExcelReader {
	public static void main(String[] args) throws IOException {
		DataGrid dataGrid = SSQTicketExcelReader.getSSQData();
		DataGridUtil.printDataGrid(dataGrid);
	}
}
