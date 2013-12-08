package com.zchaos.zutil.excel;

import java.io.IOException;

import com.zchaos.zutil.datagrid.DataGrid;
import com.zchaos.zutil.datagrid.util.DataGridUtil;
import com.zchaos.zutil.datasource.FileDataSource;

public class TestExcelReader {
	public static void main(String[] args) throws IOException {
		testXls();
	}

	public static void testXls() throws IOException {
		String path = "test.xls";
		FileDataSource datasource = new FileDataSource(path, TestExcelReader.class);
		DataGrid dataGrid = ExcelReader.excel2DataGrid(datasource);
		DataGridUtil.printDataGrid(dataGrid);
	}

	public static void testXlsx() throws IOException {
		String path = "test.xlsx";
		FileDataSource datasource = new FileDataSource(path, TestExcelReader.class);
		DataGrid dataGrid = ExcelReader.excel2DataGrid(datasource);
		DataGridUtil.printDataGrid(dataGrid);
	}
}
