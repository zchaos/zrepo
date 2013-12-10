package com.zchaos.zutil.datagrid.impl;

import com.zchaos.zutil.datagrid.DataGridCell;

/**
 * 数据单元格
 * @author zhuchx
 *
 */
public class DataGridCellImpl implements DataGridCell {
	private Object value;

	public DataGridCellImpl(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return this.value;
	}
}
