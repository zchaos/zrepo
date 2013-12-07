package com.zchaos.zutil.datagrid.dynamic;

import java.util.ArrayList;
import java.util.List;

import com.zchaos.zutil.datagrid.DataGridCell;
import com.zchaos.zutil.datagrid.DataGridHeadCell;
import com.zchaos.zutil.datagrid.DataGridHeadCol;
import com.zchaos.zutil.datagrid.DataGridHeadRow;

/**
 * 动态构建数据表格
 * @author zhuchx
 *
 */
public class DataGridDynamic {
	private DataGridHeadCol headObjs = new DataGridHeadCol();

	private List<DataGridHeadRow> rowObjs = new ArrayList<DataGridHeadRow>();

	public void addHeadCell(int col, DataGridHeadCell headCell) {
		headObjs.addHeadCell(col, headCell);
	}

	public void addCell(int row, int col, DataGridCell cell) {
		DataGridHeadRow rowObj = getRow(row);
		rowObj.addCell(col, cell);
	}

	private DataGridHeadRow getRow(int row) {
		DataGridHeadRow rowObj = null;
		if (row < rowObjs.size()) {
			rowObj = rowObjs.get(row);
		}
		if (rowObj == null) {
			rowObj = new DataGridHeadRow();
			rowObjs.set(row, rowObj);
		}
		return rowObj;
	}
}
