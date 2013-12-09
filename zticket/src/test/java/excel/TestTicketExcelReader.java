package excel;

import java.io.IOException;

import com.zchaos.zutil.datagrid.DataGrid;
import com.zchaos.zutil.datagrid.util.DataGridUtil;

public class TestTicketExcelReader {
	public static void main(String[] args) throws IOException {
		DataGrid dataGrid = TicketExcelReader.getSSQData();
		DataGridUtil.printDataGrid(dataGrid);
	}
}
