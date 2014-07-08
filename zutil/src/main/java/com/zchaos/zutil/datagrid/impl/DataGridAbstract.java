package com.zchaos.zutil.datagrid.impl;

import java.io.IOException;
import java.io.StringWriter;

import com.zchaos.zutil.datagrid.DataGrid;
import com.zchaos.zutil.datagrid.DataGridHead;
import com.zchaos.zutil.datagrid.util.DataGridUtil;

public abstract class DataGridAbstract implements DataGrid {
	private DataGridHead leftHead;

	private DataGridHead rightHead;

	private DataGridHead topHead;

	private DataGridHead bottomHead;

	public DataGridHead getTopHead() {
		return topHead;
	}

	public DataGridHead getBottomHead() {
		return bottomHead;
	}

	public DataGridHead getLeftHead() {
		return leftHead;
	}

	public DataGridHead getRightHead() {
		return rightHead;
	}

	public void setTopHead(DataGridHead head) {
		this.topHead = head;
	}

	public void setBottomHead(DataGridHead head) {
		this.bottomHead = head;
	}

	public void setLeftHead(DataGridHead head) {
		this.leftHead = head;
	}

	public void setRightHead(DataGridHead head) {
		this.rightHead = head;
	}

	public String toString() {
		StringWriter w = new StringWriter(100);
		try {
			DataGridUtil.writeDataGrid(this, w);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		return w.toString();
	}
}
