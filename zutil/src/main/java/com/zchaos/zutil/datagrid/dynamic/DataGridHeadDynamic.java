package com.zchaos.zutil.datagrid.dynamic;

import com.zchaos.zutil.datagrid.DataGridHead;
import com.zchaos.zutil.datagrid.DataGridHeadCell;
import com.zchaos.zutil.datagrid.impl.DataGridHeadCellImpl;
import com.zchaos.zutil.datagrid.util.DataGridListDynamic;

public class DataGridHeadDynamic implements DataGridHead {
	private DataGridListDynamic<DataGridHeadCellImpl> cells = new DataGridListDynamic<DataGridHeadCellImpl>();

	public void addCell(int index, DataGridHeadCellImpl headCell) {
		cells.set(index, headCell);
	}

	public DataGridHeadCell getCell(int index) {
		return cells.get(index);
	}
}
