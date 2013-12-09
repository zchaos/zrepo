package excel;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.zchaos.zutil.ExcelUtils;
import com.zchaos.zutil.StringUtils;
import com.zchaos.zutil.datagrid.DataGrid;
import com.zchaos.zutil.datagrid.DataGridHeadCell;
import com.zchaos.zutil.datagrid.dynamic.DataGridDynamic;
import com.zchaos.zutil.datagrid.impl.DataGridCellImpl;
import com.zchaos.zutil.datagrid.impl.DataGridHeadCellImpl;
import com.zchaos.zutil.datagrid.util.DataGridUtil;
import com.zchaos.zutil.excel.ExcelDataRect;

public class TicketExcelReader {
	/**
	 * 返回双色球的数据
	 * @param ssq
	 * @return
	 * @throws IOException 
	 */
	public static DataGrid getSSQData() throws IOException {
		String path = "/data/ssq/ssq.xlsx";
		Workbook workBook = ExcelUtils.createWorkBook(path, TicketExcelReader.class);
		DataGridDynamic dataGrid = new DataGridDynamic();

		int col = 0;
		dataGrid.addTopHeadCell(col++, new DataGridHeadCellImpl("期号"));
		dataGrid.addTopHeadCell(col++, new DataGridHeadCellImpl("红球1"));
		dataGrid.addTopHeadCell(col++, new DataGridHeadCellImpl("红球2"));
		dataGrid.addTopHeadCell(col++, new DataGridHeadCellImpl("红球3"));
		dataGrid.addTopHeadCell(col++, new DataGridHeadCellImpl("红球4"));
		dataGrid.addTopHeadCell(col++, new DataGridHeadCellImpl("红球5"));
		dataGrid.addTopHeadCell(col++, new DataGridHeadCellImpl("红球6"));
		dataGrid.addTopHeadCell(col++, new DataGridHeadCellImpl("篮球"));

		int len = workBook.getNumberOfSheets();
		for (int i = 0; i < len; i++) {
			Sheet sheet = workBook.getSheetAt(i);
			sheet2DataGrid(dataGrid, sheet);
		}
		return DataGridUtil.toFixed(dataGrid);
	}

	private static void sheet2DataGrid(DataGridDynamic dataGrid, Sheet sheet) throws IOException {
		ExcelDataRect rect = ExcelUtils.getSheetRect(sheet);
		int rowIndex = dataGrid.getRowCount();
		int startRow = rect.getRow();
		int col = rect.getCol();
		int endRow = startRow + rect.getRowCount();

		for (int i = startRow; i < endRow; i++) {
			Row sheetRow = sheet.getRow(i);
			if (isHead(dataGrid, sheetRow)) {
				continue;
			}
			short startCol = sheetRow.getFirstCellNum();
			short endCol = sheetRow.getLastCellNum();

			for (int j = startCol; j < endCol; j++) {
				Cell cell = sheetRow.getCell(j);
				String value = ExcelUtils.sheet2DataGrid(cell);

				DataGridCellImpl dataGridCell = new DataGridCellImpl(value);

				dataGrid.addCell(rowIndex, j - col, dataGridCell);
			}

			rowIndex++;
		}
	}

	/**
	 * 判断是否头部
	 * @param dataGrid
	 * @param sheetRow
	 * @return
	 * @throws IOException 
	 */
	private static boolean isHead(DataGrid dataGrid, Row sheetRow) throws IOException {
		short startCol = sheetRow.getFirstCellNum();
		Cell cell = sheetRow.getCell(startCol);
		String value = ExcelUtils.sheet2DataGrid(cell);

		DataGridHeadCell headCell = dataGrid.getTopHeadCell(0);
		return StringUtils.equalsIgnoreCase(value, headCell.getValue());
	}
}
