package com.zchaos.zutil.datagrid;

/**
 * 数据表格。包括数据行DataGridRow和数据列DataGridCol。数据行和数据列中的元素是DataGridCell
 * @author zhuchx
 *
 */
public class DataGrid {
	private DataGridHeadRow leftRow;

	private DataGridHeadRow rightRow;

	private DataGridHeadCol topCol;

	private DataGridHeadCol bottomCol;

	private DataGridCell[][] cells;

	private int rowcount;

	private int colcount;

	public DataGrid(int rowcount, int colcount) {
		this.rowcount = rowcount;
		this.colcount = colcount;

		cells = new DataGridCell[rowcount][colcount];
	}
}
