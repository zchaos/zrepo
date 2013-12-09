package com.zchaos.zutil.excel;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Workbook;

import com.zchaos.zutil.ExcelUtils;
import com.zchaos.zutil.FileUtils;
import com.zchaos.zutil.datagrid.DataGrid;
import com.zchaos.zutil.datagrid.util.DataGridUtil;
import com.zchaos.zutil.datasource.FileDataSource;

public class TestExcelUtils {
	public static void main(String[] args) throws IOException {
		testWorkBook();
	}

	public static void testXls() throws IOException {
		String path = "test.xls";
		FileDataSource datasource = new FileDataSource(path, TestExcelUtils.class);
		DataGrid dataGrid = ExcelUtils.excel2DataGrid(datasource);
		DataGridUtil.printDataGrid(dataGrid);
	}

	public static void testXlsx() throws IOException {
		String path = "test.xlsx";
		FileDataSource datasource = new FileDataSource(path, TestExcelUtils.class);
		DataGrid dataGrid = ExcelUtils.excel2DataGrid(datasource);
		DataGridUtil.printDataGrid(dataGrid);
	}

	public static void testWorkBook() throws IOException {
		String path = "test.xls";
		String ext = FileUtils.getExt(path);
		Workbook workBook = null;
		InputStream in = TestExcelUtils.class.getResourceAsStream(path);
		try {
			workBook = ExcelUtils.createWorkBook(in, ext);
		}
		finally {
			in.close();
		}

		DataGrid dataGrid = ExcelUtils.workBook2DataGrid(workBook);
		DataGridUtil.printDataGrid(dataGrid);
	}
}
