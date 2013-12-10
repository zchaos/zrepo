package com.zchaos.zutil.datagrid;

/**
 * 表格行头。行头可以在左边，右边，上边和下边
 * @author zhuchx
 *
 */
public interface DataGridHead {
	public DataGridHeadCell getCell(int index);

	public void addCell(int index, DataGridHeadCell headCell);

	public int size();
}
