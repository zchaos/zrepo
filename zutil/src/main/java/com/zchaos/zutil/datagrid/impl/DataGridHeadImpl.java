package com.zchaos.zutil.datagrid.impl;

import com.zchaos.zutil.datagrid.DataGridHead;
import com.zchaos.zutil.datagrid.DataGridHeadCell;

public class DataGridHeadImpl implements DataGridHead {
	private DataGridHeadCell[] cells;

	public DataGridHeadImpl(int count) {
		cells = new DataGridHeadCell[count];
	}

	public void addCell(int index, DataGridHeadCell headCell) {
		cells[index] = headCell;
	}

	public DataGridHeadCell getCell(int index) {
		return cells[index];
	}

	public int size() {
		return cells.length;
	}

}
