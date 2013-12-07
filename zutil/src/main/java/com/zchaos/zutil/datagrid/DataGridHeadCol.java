package com.zchaos.zutil.datagrid;

/**
 * 数据列信息
 * @author zhuchx
 *
 */
public class DataGridHeadCol extends DataGridHead {
	public DataGridHeadCol(int count) {
		super(count);
	}

	public void addHeadCell(int col, DataGridHeadCell headCell) {
		addCell(col, headCell);
	}
}
