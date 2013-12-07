package com.zchaos.zutil.datagrid.dynamic;

import java.util.ArrayList;
import java.util.List;

import com.zchaos.zutil.datagrid.DataGridHeadCell;

public abstract class DataGridHeadDynamic {
	private List<DataGridHeadCell> cells = new ArrayList<DataGridHeadCell>();

	protected void addCell(int index, DataGridHeadCell headCell) {
		ensureSize(index);
		cells.set(index, headCell);
	}

	public abstract void addHeadCell(int index, DataGridHeadCell headCell);

	private void ensureSize(int index) {
		int size = cells.size();
		if (index < size) {
			return;
		}
		
		int len = index + 1;
		for (int i = size; i < len; i++) {
			cells.set(0, null);
		}
	}
}
