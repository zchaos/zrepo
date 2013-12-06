package com.zchaos.zutil.datagrid;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据行。数据行中的数据是DataGridCell
 * @author zhuchx
 *
 */
public class DataGridRow {
	private List<DataGridCell> cells = new ArrayList<DataGridCell>();

	public void addCell(int col, DataGridCell cell) {
		cells.set(col, cell);
	}
}
