package com.zchaos.zutil.datagrid;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据表格。包括数据行DataGridRow和数据列DataGridCol。数据行和数据列中的元素是DataGridCell
 * @author zhuchx
 *
 */
public class DataGrid {
	private DataGridHead headObjs = new DataGridHead();

	private List<DataGridRow> rowObjs = new ArrayList<DataGridRow>();

	public void addHeadCell(int col, DataGridHeadCell headCell) {
		headObjs.addHeadCell(col, headCell);
	}

	public void addCell(int row, int col, DataGridCell cell) {
		DataGridRow rowObj = getRow(row);
		rowObj.addCell(col, cell);
	}

	private DataGridRow getRow(int row) {
		DataGridRow rowObj = null;
		if (row < rowObjs.size()) {
			rowObj = rowObjs.get(row);
		}
		if (rowObj == null) {
			rowObj = new DataGridRow();
			rowObjs.set(row, rowObj);
		}
		return rowObj;
	}
}
