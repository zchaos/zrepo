package com.zchaos.zutil.datagrid.impl;

import com.zchaos.zutil.datagrid.DataGrid;
import com.zchaos.zutil.datagrid.DataGridCell;

/**
 * 数据表格。包括数据行DataGridRow和数据列DataGridCol。数据行和数据列中的元素是DataGridCell
 * @author zhuchx
 *
 */
public class DataGridImpl implements DataGrid {
	//	private DataGridHead leftHead;
	//
	//	private DataGridHead rightHead;
	//
	//	private DataGridHead topHead;
	//
	//	private DataGridHead bottomHead;

	private DataGridCell[][] cells;

	private int rowcount;

	private int colcount;

	public DataGridImpl(int rowcount, int colcount) {
		this.rowcount = rowcount;
		this.colcount = colcount;

		cells = new DataGridCellImpl[rowcount][colcount];
	}

	public void addCell(int row, int col, DataGridCell cell) {
		cells[row][col] = cell;
	}

	public DataGridCell getCell(int row, int col) {
		return cells[row][col];
	}

	public int getRowCount() {
		return rowcount;
	}

	public int getColCount() {
		return colcount;
	}
}
