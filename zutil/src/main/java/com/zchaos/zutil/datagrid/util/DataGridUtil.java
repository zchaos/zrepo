package com.zchaos.zutil.datagrid.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import com.zchaos.zutil.StringUtils;
import com.zchaos.zutil.datagrid.DataGrid;
import com.zchaos.zutil.datagrid.DataGridCell;
import com.zchaos.zutil.datagrid.DataGridHead;
import com.zchaos.zutil.datagrid.DataGridHeadCell;
import com.zchaos.zutil.datagrid.dynamic.DataGridDynamic;
import com.zchaos.zutil.datagrid.impl.DataGridCellImpl;
import com.zchaos.zutil.datagrid.impl.DataGridImpl;

/**
 * DataGrid的工具类
 * @author zhuchx
 *
 */
public class DataGridUtil {
	public static DataGrid toFixed(DataGridDynamic dynamicDataGrid) {
		int rowcount = dynamicDataGrid.getRowCount();
		int colcount = dynamicDataGrid.getColCount();

		DataGridImpl dataGrid = new DataGridImpl(rowcount, colcount);

		for (int i = 0; i < rowcount; i++) {
			for (int j = 0; j < colcount; j++) {
				DataGridCell cell = dynamicDataGrid.getCell(i, j);
				if (cell == null) {
					cell = new DataGridCellImpl(null);
				}
				dataGrid.addCell(i, j, cell);
			}
		}

		return dataGrid;
	}

	public static void printDataGrid(DataGrid dataGrid) throws IOException {
		StringWriter w = new StringWriter();
		DataGridUtil.writeDataGrid(dataGrid, w);
		System.out.println(w.toString());
	}

	public static void writeDataGrid(DataGrid dataGrid, Writer w) throws IOException {
		DataGridHead head = dataGrid.getTopHead();
		if (head != null && head.size() > 0) {
			int size = head.size();
			for (int i = 0; i < size; i++) {
				if (i != 0) {
					w.write('\t');
				}
				DataGridHeadCell cell = head.getCell(i);
				if (cell == null) {
					continue;
				}
				String value = StringUtils.obj2str(cell.getValue());
				if (StringUtils.isNotEmpty(value)) {
					w.write(value);
				}
			}
			w.write("\r\n");
		}

		int rowcount = dataGrid.getRowCount();
		int colcount = dataGrid.getColCount();
		for (int i = 0; i < rowcount; i++) {
			if (i != 0) {
				w.write("\r\n");
			}
			for (int j = 0; j < colcount; j++) {
				if (j != 0) {
					w.write('\t');
				}
				DataGridCell cell = dataGrid.getCell(i, j);
				if (cell == null) {
					continue;
				}
				String value = StringUtils.obj2str(cell.getValue());
				if (StringUtils.isNotEmpty(value)) {
					w.write(value);
				}
			}
		}
	}
}
