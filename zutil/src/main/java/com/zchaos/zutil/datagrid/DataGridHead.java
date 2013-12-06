package com.zchaos.zutil.datagrid;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据列头，数据列中的数据是DataGridHeadCell
 * @author zhuchx
 *
 */
public class DataGridHead {
	private List<DataGridHeadCell> cells = new ArrayList<DataGridHeadCell>();

	public void addHeadCell(int col, DataGridHeadCell headCell) {
		cells.set(col, headCell);
	}
}
