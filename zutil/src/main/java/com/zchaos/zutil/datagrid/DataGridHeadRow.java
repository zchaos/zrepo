package com.zchaos.zutil.datagrid;

/**
 * 数据行信息
 * @author zhuchx
 *
 */
public class DataGridHeadRow extends DataGridHead {

	public DataGridHeadRow(int count) {
		super(count);
	}

	public void addHeadCell(int col, DataGridHeadCell headCell) {
		addCell(col, headCell);
	}
}
