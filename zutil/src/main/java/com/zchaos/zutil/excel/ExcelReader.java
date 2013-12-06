package com.zchaos.zutil.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.zchaos.zutil.datagrid.DataGrid;
import com.zchaos.zutil.datagrid.DataGridCell;

/**
 * 读取excel文件
 * @author zhuchx
 *
 */
public class ExcelReader {
	public static DataGrid excel2DataGrid(InputStream in, String ext) throws IOException {
		Workbook workBook = createWorkBook(in, ext);
		return workBook2DataGrid(workBook);
	}

	/**
	 * 生成excel的workbook
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static Workbook createWorkBook(String path) throws IOException {
		if (org.apache.commons.lang.StringUtils.isBlank(path)) {
			throw new IllegalArgumentException("参数错误!!!");
		}
		if (StringUtils.endsWithIgnoreCase(path, ".xls")) {
			return new HSSFWorkbook(new FileInputStream(path));
		}
		else if (StringUtils.endsWithIgnoreCase(path, ".xlsx")) {
			return new XSSFWorkbook(new FileInputStream(path));
		}
		else {
			throw new IllegalArgumentException("不支持除：xls/xlsx以外的文件格式!!!");
		}
	}

	public static Workbook createWorkBook(InputStream in, String ext) throws IOException {
		if (StringUtils.equalsIgnoreCase(ext, "xls")) {
			return new HSSFWorkbook(in);
		}
		else if (StringUtils.equalsIgnoreCase(ext, "xlsx")) {
			return new XSSFWorkbook(in);
		}
		else {
			throw new IllegalArgumentException("不支持除：xls/xlsx以外的文件格式!!!");
		}
	}

	public static DataGrid workBook2DataGrid(Workbook workBook) throws IOException {
		DataGrid dataGrid = new DataGrid();

		int len = workBook.getNumberOfSheets();
		for (int i = 0; i < len; i++) {
			Sheet sheet = workBook.getSheetAt(i);
			sheet2DataGrid(dataGrid, sheet);
		}
		return dataGrid;
	}

	public static void sheet2DataGrid(DataGrid dataGrid, Sheet sheet) throws IOException {
		int startRow = sheet.getFirstRowNum();
		int endRow = sheet.getLastRowNum();

		//int leftCol = sheet.getLeftCol();
		for (int i = startRow; i < endRow; i++) {
			Row row = sheet.getRow(i);
			short startCol = row.getFirstCellNum();
			short endCol = row.getLastCellNum();

			for (int j = startCol; j < endCol; j++) {
				Cell cell = row.getCell(j);
				String value = sheet2DataGrid(cell);

				DataGridCell dataGridCell = new DataGridCell(value);

				dataGrid.addCell(i - startRow, j - startCol, dataGridCell);
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