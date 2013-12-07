package com.zchaos.zutil.datagrid;

/**
 * 数据表格。包括数据行DataGridRow和数据列DataGridCol。数据行和数据列中的元素是DataGridCell
 * @author zhuchx
 *
 */
public interface DataGrid {
	public int getRowCount();

	public int getColCount();

	public void addCell(int row, int col, DataGridCell cell);

	public DataGridCell getCell(int row, int col);
}
