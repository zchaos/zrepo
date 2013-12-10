package com.zchaos.zutil.datagrid.dynamic;

import com.zchaos.zutil.datagrid.DataGridCell;
import com.zchaos.zutil.datagrid.impl.DataGridAbstract;
import com.zchaos.zutil.datagrid.util.DataGridListDynamic;

/**
 * 动态构建数据表格
 * @author zhuchx
 *
 */
public class DataGridDynamic extends DataGridAbstract {
	private DataGridListDynamic<DataGridListDynamic<DataGridCell>> cells = new DataGridListDynamic<DataGridListDynamic<DataGridCell>>();

	public void addCell(int row, int col, DataGridCell cell) {
		DataGridListDynamic<DataGridCell> list = cells.get(row);
		if (list == null) {
			list = new DataGridListDynamic<DataGridCell>();
			cells.set(row, list);
		}
		list.set(col, cell);
	}

	public DataGridCell getCell(int row, int col) {
		DataGridListDynamic<DataGridCell> list = cells.get(row);
		return list == null ? null : list.get(col);
	}

	public int getRowCount() {
		return cells.size();
	}

	public int getColCount() {
		int rowcount = cells.size();
		int colcount = 0;
		for (int i = 0; i < rowcount; i++) {
			DataGridListDynamic<DataGridCell> list = cells.get(i);
			colcount = Math.max(colcount, list == null ? 0 : list.size());
		}
		return colcount;
	}
}
