package com.zchaos.zutil.datagrid.impl;

import com.zchaos.zutil.datagrid.DataGrid;
import com.zchaos.zutil.datagrid.DataGridCell;
import com.zchaos.zutil.datagrid.DataGridHead;
import com.zchaos.zutil.datagrid.DataGridHeadCell;

/**
 * 数据表格。包括数据行DataGridRow和数据列DataGridCol。数据行和数据列中的元素是DataGridCell
 * @author zhuchx
 *
 */
public class DataGridImpl implements DataGrid {
	private DataGridHead leftHead;

	private DataGridHead rightHead;

	private DataGridHead topHead;

	private DataGridHead bottomHead;

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

	@Override
	public DataGridHeadCell getTopHeadCell(int col) {
		return topHead == null ? null : topHead.getCell(col);
	}

	@Override
	public DataGridHeadCell getBottomHeadCell(int col) {
		return bottomHead == null ? null : bottomHead.getCell(col);
	}

	@Override
	public DataGridHeadCell getLeftHeadCell(int row) {
		return leftHead == null ? null : leftHead.getCell(row);
	}

	@Override
	public DataGridHeadCell getRightHeadCell(int row) {
		return rightHead == null ? null : rightHead.getCell(row);
	}

	@Override
	public void addTopHeadCell(int col, DataGridHeadCell headCell) {
		if (topHead == null) {
			topHead = new DataGridHeadImpl(colcount);
		}
		topHead.addCell(col, headCell);
	}

	@Override
	public void addBottomHeadCell(int col, DataGridHeadCell headCell) {
		if (bottomHead == null) {
			bottomHead = new DataGridHeadImpl(colcount);
		}
		bottomHead.addCell(col, headCell);
	}

	@Override
	public void addLeftHeadCell(int col, DataGridHeadCell headCell) {
		if (leftHead == null) {
			leftHead = new DataGridHeadImpl(colcount);
		}
		leftHead.addCell(col, headCell);
	}

	@Override
	public void addRightHeadCell(int col, DataGridHeadCell headCell) {
		if (rightHead == null) {
			rightHead = new DataGridHeadImpl(colcount);
		}
		rightHead.addCell(col, headCell);
	}

	@Override
	public boolean hasTopHead() {
		return topHead != null;
	}

	@Override
	public boolean hasBottomHead() {
		return bottomHead != null;
	}

	@Override
	public boolean hasLeftHead() {
		return leftHead != null;
	}

	@Override
	public boolean hasRightHead() {
		return rightHead != null;
	}
}
