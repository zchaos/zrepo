package excel;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.zchaos.zutil.ExcelUtils;
import com.zchaos.zutil.NumberUtils;
import com.zchaos.zutil.StringUtils;
import com.zchaos.zutil.datagrid.DataGrid;
import com.zchaos.zutil.datagrid.DataGridCell;
import com.zchaos.zutil.datagrid.DataGridHead;
import com.zchaos.zutil.datagrid.DataGridHeadCell;
import com.zchaos.zutil.datagrid.dynamic.DataGridDynamic;
import com.zchaos.zutil.datagrid.impl.DataGridCellImpl;
import com.zchaos.zutil.datagrid.impl.DataGridHeadCellImpl;
import com.zchaos.zutil.datagrid.impl.DataGridHeadImpl;
import com.zchaos.zutil.datagrid.impl.DataGridImpl;
import com.zchaos.zutil.excel.ExcelDataRect;

public class SSQTicketExcelReader {
	/**
	 * 返回双色球的数据
	 * @param ssq
	 * @return
	 * @throws IOException 
	 */
	public static DataGrid getSSQData() {
		String path = "/data/ssq/ssq.xlsx";
		return getSSQData(path, null);
	}

	public static DataGrid getSSQData(String path, String sheetName) {
		try {
			Workbook workBook = ExcelUtils.createWorkBook(path, SSQTicketExcelReader.class);
			DataGridDynamic dataGrid = new DataGridDynamic();

			int col = 0;
			DataGridHeadImpl head = new DataGridHeadImpl(8);
			head.addCell(col++, new DataGridHeadCellImpl("期号"));
			head.addCell(col++, new DataGridHeadCellImpl("红球1"));
			head.addCell(col++, new DataGridHeadCellImpl("红球2"));
			head.addCell(col++, new DataGridHeadCellImpl("红球3"));
			head.addCell(col++, new DataGridHeadCellImpl("红球4"));
			head.addCell(col++, new DataGridHeadCellImpl("红球5"));
			head.addCell(col++, new DataGridHeadCellImpl("红球6"));
			head.addCell(col++, new DataGridHeadCellImpl("篮球"));
			dataGrid.setTopHead(head);

			int len = workBook.getNumberOfSheets();
			for (int i = 0; i < len; i++) {
				Sheet sheet = workBook.getSheetAt(i);

				if (StringUtils.isEmpty(sheetName) || StringUtils.equalsIgnoreCase(sheetName, sheet.getSheetName())) {
					sheet2DataGrid(dataGrid, sheet);
				}
			}
			return toFixed(dataGrid);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获得某一年的数据
	 * @param year
	 * @return
	 */
	public static DataGrid getSSQData(int year) {
		try {
			String yearPath = "/data/ssq/ssq-" + year + ".xlsx";
			if (ExcelUtils.exist(yearPath, SSQTicketExcelReader.class)) {
				return getSSQData(yearPath, null);
			}
			else {
				String path = "/data/ssq/ssq.xlsx";
				return getSSQData(path, String.valueOf(year));
			}
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
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

		DataGridHead head = dataGrid.getTopHead();
		if (head == null || head.size() == 0) {
			return false;
		}
		DataGridHeadCell headCell = head.getCell(0);
		String headValue = StringUtils.obj2str(headCell.getValue());
		return StringUtils.equalsIgnoreCase(value, headValue);
	}

	public static DataGrid toFixed(DataGridDynamic dynamicDataGrid) {
		int rowcount = dynamicDataGrid.getRowCount();
		int colcount = dynamicDataGrid.getColCount();

		dynamicDataGrid.toString();

		//会将期号分为年份和期号两部分，所以列会加1
		DataGridImpl dataGrid = new DataGridImpl(rowcount, colcount + 1);
		int col = 0;
		DataGridHeadImpl head = new DataGridHeadImpl(9);
		head.addCell(col++, new DataGridHeadCellImpl("年份"));
		head.addCell(col++, new DataGridHeadCellImpl("期号"));
		head.addCell(col++, new DataGridHeadCellImpl("红球1"));
		head.addCell(col++, new DataGridHeadCellImpl("红球2"));
		head.addCell(col++, new DataGridHeadCellImpl("红球3"));
		head.addCell(col++, new DataGridHeadCellImpl("红球4"));
		head.addCell(col++, new DataGridHeadCellImpl("红球5"));
		head.addCell(col++, new DataGridHeadCellImpl("红球6"));
		head.addCell(col++, new DataGridHeadCellImpl("篮球"));
		dataGrid.setTopHead(head);

		for (int i = 0; i < rowcount; i++) {
			for (int j = 0; j < colcount; j++) {
				DataGridCell cell = dynamicDataGrid.getCell(i, j);
				String value = StringUtils.obj2str((int) Math.floor(NumberUtils.toDouble(cell.getValue())));
				if (j == 0) {
					String year = value.substring(0, value.length() - 3);
					String num = value.substring(year.length());

					DataGridCellImpl ycell = new DataGridCellImpl(NumberUtils.toInt(year) + 2000);
					DataGridCellImpl ncell = new DataGridCellImpl(NumberUtils.toInt(num));
					dataGrid.addCell(i, j, ycell);
					dataGrid.addCell(i, j + 1, ncell);
				}
				else {
					DataGridCellImpl ncell = new DataGridCellImpl(NumberUtils.toInt(value));
					dataGrid.addCell(i, j + 1, ncell);
				}
			}
		}

		return dataGrid;
	}
}
