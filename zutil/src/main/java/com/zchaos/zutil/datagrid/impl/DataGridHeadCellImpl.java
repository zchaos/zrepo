package com.zchaos.zutil.datagrid.impl;

import com.zchaos.zutil.datagrid.DataGridHeadCell;

/**
 * 数据列头单元格
 * @author zhuchx
 *
 */
public class DataGridHeadCellImpl implements DataGridHeadCell {
	private Object value;

	public DataGridHeadCellImpl(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return this.value;
	}
}
