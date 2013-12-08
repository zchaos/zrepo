package com.zchaos.zutil.datagrid.dynamic;

import com.zchaos.zutil.datagrid.DataGrid;
import com.zchaos.zutil.datagrid.DataGridCell;
import com.zchaos.zutil.datagrid.DataGridHeadCell;
import com.zchaos.zutil.datagrid.util.DataGridListDynamic;

/**
 * 动态构建数据表格
 * @author zhuchx
 *
 */
public class DataGridDynamic implements DataGrid {
	private DataGridHeadDynamic leftHead = null;

	private DataGridHeadDynamic topHead = null;

	private DataGridHeadDynamic rightHead = null;

	private DataGridHeadDynamic bottomHead = null;

	private DataGridListDynamic<DataGridListDynamic<DataGridCell>> cells = new DataGridListDynamic<DataGridListDynamic<DataGridCell>>();

	public void addCell(int row, int col, DataGridCell cell) {
		DataGridListDynamic<DataGridCell> list = cells.get(row);
		if (list == null) {
			list = new DataGridListDynamic<DataGridCell>();
			cells.set(row, list);
		}
		list.set(col, cell);
	}

	public DataGridCell getCell(int row, int col) {
		DataGridListDynamic<DataGridCell> list = cells.get(row);
		return list == null ? null : list.get(col);
	}

	public int getRowCount() {
		return cells.size();
	}

	public int getColCount() {
		int rowcount = cells.size();
		int colcount = 0;
		for (int i = 0; i < rowcount; i++) {
			DataGridListDynamic<DataGridCell> list = cells.get(i);
			colcount = Math.max(colcount, list == null ? 0 : list.size());
		}
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
			topHead = new DataGridHeadDynamic();
		}
		topHead.addCell(col, headCell);
	}

	@Override
	public void addBottomHeadCell(int col, DataGridHeadCell headCell) {
		if (bottomHead == null) {
			bottomHead = new DataGridHeadDynamic();
		}
		bottomHead.addCell(col, headCell);
	}

	@Override
	public void addLeftHeadCell(int col, DataGridHeadCell headCell) {
		if (leftHead == null) {
			leftHead = new DataGridHeadDynamic();
		}
		leftHead.addCell(col, headCell);
	}

	@Override
	public void addRightHeadCell(int col, DataGridHeadCell headCell) {
		if (rightHead == null) {
			rightHead = new DataGridHeadDynamic();
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
