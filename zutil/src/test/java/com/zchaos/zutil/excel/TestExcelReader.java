package com.zchaos.zutil.excel;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import com.zchaos.zutil.datagrid.DataGrid;
import com.zchaos.zutil.datagrid.impl.DataGridImpl;
import com.zchaos.zutil.datagrid.util.DataGridUtil;

public class TestExcelReader {
	public static void main(String[] args) throws IOException {
		testXls();
	}

	public static void testXls() throws IOException {
		String ext = "xls";
		String path = "test.xls";
		InputStream in = TestExcelReader.class.getResourceAsStream(path);
		try {
			DataGrid dataGrid = ExcelReader.excel2DataGrid(in, ext);
			DataGridUtil.printDataGrid(dataGrid);
		}
		finally {
			in.close();
		}
	}

	public static void testXlsx() throws IOException {
		String ext = "xlsx";
		String path = "test.xlsx";
		InputStream in = TestExcelReader.class.getResourceAsStream(path);
		try {
			DataGrid dataGrid = ExcelReader.excel2DataGrid(in, ext);
			DataGridUtil.printDataGrid(dataGrid);
		}
		finally {
			in.close();
		}
	}
}
