package com.zchaos.zutil.datagrid;

public abstract class DataGridHead {
	private DataGridHeadCell[] cells;

	public DataGridHead(int count) {
		cells = new DataGridHeadCell[count];
	}

	protected void addCell(int index, DataGridHeadCell headCell) {
		cells[index] = headCell;
	}

	public abstract void addHeadCell(int index, DataGridHeadCell headCell);

}
