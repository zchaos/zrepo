package com.zchaos.zutil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.zchaos.zutil.datagrid.DataGrid;
import com.zchaos.zutil.datagrid.dynamic.DataGridDynamic;
import com.zchaos.zutil.datagrid.impl.DataGridCellImpl;
import com.zchaos.zutil.datagrid.util.DataGridUtil;
import com.zchaos.zutil.datasource.FileDataSource;
import com.zchaos.zutil.excel.ExcelDataRect;

public class ExcelUtils {
	public static final String EXT_XLS = ".xls";

	public static final String EXT_XLSX = ".xlsx";

	public static Workbook createWorkBook(InputStream in, String ext) throws IOException {
		checkExt(ext);
		if (StringUtils.equalsIgnoreCase(ext, EXT_XLS)) {
			return new HSSFWorkbook(in);
		}
		else {
			return new XSSFWorkbook(in);
		}
	}

	public static Workbook createWorkBook(String path) throws IOException {
		String ext = FileUtils.getExt(path);
		InputStream in = new FileInputStream(path);
		try {
			return createWorkBook(in, ext);
		}
		finally {
			in.close();
		}
	}

	public static Workbook createWorkBook(String path, Class<?> clazz) throws IOException {
		String ext = FileUtils.getExt(path);
		InputStream in = clazz.getResourceAsStream(path);
		try {
			return createWorkBook(in, ext);
		}
		finally {
			in.close();
		}
	}

	public static void checkExt(String ext) throws IOException {
		if (!StringUtils.equalsIgnoreCase(ext, EXT_XLS) && !StringUtils.equalsIgnoreCase(ext, EXT_XLSX)) {
			throw new IllegalArgumentException("不支持除：" + EXT_XLS + "/" + EXT_XLSX + "以外的文件格式!!!");
		}
	}

	public static DataGrid excel2DataGrid(FileDataSource fileDataSource) throws IOException {
		String ext = FileUtils.getExt(fileDataSource.getPath());
		InputStream in = fileDataSource.getStream();
		try {
			return excel2DataGrid(in, ext);
		}
		finally {
			in.close();
		}
	}

	public static DataGrid excel2DataGrid(InputStream in, String ext) throws IOException {
		Workbook workBook = createWorkBook(in, ext);
		return workBook2DataGrid(workBook);
	}

	public static DataGrid workBook2DataGrid(Workbook workBook) throws IOException {
		DataGridDynamic dataGrid = new DataGridDynamic();

		int len = workBook.getNumberOfSheets();
		for (int i = 0; i < len; i++) {
			Sheet sheet = workBook.getSheetAt(i);
			sheet2DataGrid(dataGrid, sheet);
		}
		return DataGridUtil.toFixed(dataGrid);
	}

	/**
	 * 获得sheet的数据范围
	 * @param dataGrid
	 * @param sheet
	 * @throws IOException
	 */
	/**
	 * 获得sheet的数据范围
	 * @param sheet
	 * @return 数组中的元素分别表示
	 * 		0:起始行号
	 * 		1:起始列号
	 * 		2:行数
	 * 		3:列数
	 * @throws IOException
	 */
	public static ExcelDataRect getSheetRect(Sheet sheet) throws IOException {
		int startRow = sheet.getFirstRowNum();
		int endRow = sheet.getLastRowNum();
		int rowcount = endRow - startRow + 1;

		int mincol = -1;//有数据的行中，最小的列号
		int maxcol = 0;//有数据的行中，最大的列号
		for (int i = startRow; i <= endRow; i++) {
			Row row = sheet.getRow(i);
			short startCol = row.getFirstCellNum();
			short endCol = row.getLastCellNum();
			if (mincol < 0) {
				mincol = startCol;
			}
			else {
				mincol = Math.min(mincol, startCol);
			}
			maxcol = Math.max(maxcol, endCol);
		}
		int colcount = maxcol - mincol;
		return new ExcelDataRect(startRow, mincol, rowcount, colcount);
	}

	public static void sheet2DataGrid(DataGridDynamic dataGrid, Sheet sheet) throws IOException {
		ExcelDataRect rect = getSheetRect(sheet);
		int startRow = rect.getRow();
		int col = rect.getCol();
		int endRow = startRow + rect.getRowCount();

		for (int i = startRow; i < endRow; i++) {
			Row sheetRow = sheet.getRow(i);
			short startCol = sheetRow.getFirstCellNum();
			short endCol = sheetRow.getLastCellNum();

			for (int j = startCol; j < endCol; j++) {
				Cell cell = sheetRow.getCell(j);
				String value = sheet2DataGrid(cell);

				DataGridCellImpl dataGridCell = new DataGridCellImpl(value);

				dataGrid.addCell(i - startRow, j - col, dataGridCell);
			}
		}
	}

	public static String sheet2DataGrid(Cell cell) throws IOException {
		if (cell == null) {
			return null;
		}
		String value = null;
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC: // 数字
				if (HSSFDateUtil.isCellDateFormatted(cell)) { // 如果是日期类型
					value = new SimpleDateFormat().format(cell.getDateCellValue());
				}
				else {
					value = String.valueOf(cell.getNumericCellValue());
				}
				break;
			case Cell.CELL_TYPE_STRING: // 字符串
				value = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_FORMULA: // 公式
				// 用数字方式获取公式结果，根据值判断是否为日期类型
				double numericValue = cell.getNumericCellValue();
				if (HSSFDateUtil.isValidExcelDate(numericValue)) { // 如果是日期类型
					value = new SimpleDateFormat().format(cell.getDateCellValue());
				}
				else {
					value = String.valueOf(numericValue);
				}
				break;
			case Cell.CELL_TYPE_BLANK: // 空白
				value = StringUtils.EMPTY;
				break;
			case Cell.CELL_TYPE_BOOLEAN: // Boolean
				value = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_ERROR: // Error，返回错误码
				value = String.valueOf(cell.getErrorCellValue());
				break;
			default:
				value = StringUtils.EMPTY;
				break;
		}
		// 使用[]记录坐标
		return value;
	}
}
