package com.zchaos.zutil.datagrid.impl;

import com.zchaos.zutil.datagrid.DataGridHead;
import com.zchaos.zutil.datagrid.DataGridHeadCell;

public class DataGridHeadImpl implements DataGridHead {
	private DataGridHeadCellImpl[] cells;

	public DataGridHeadImpl(int count) {
		cells = new DataGridHeadCellImpl[count];
	}

	public void addCell(int index, DataGridHeadCellImpl headCell) {
		cells[index] = headCell;
	}

	public DataGridHeadCell getCell(int index) {
		return cells[index];
	}

}
