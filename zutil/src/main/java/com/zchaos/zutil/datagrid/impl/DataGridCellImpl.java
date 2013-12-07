package com.zchaos.zutil.datagrid.impl;

import com.zchaos.zutil.datagrid.DataGridCell;

/**
 * 数据单元格
 * @author zhuchx
 *
 */
public class DataGridCellImpl implements DataGridCell {
	private String value;

	public DataGridCellImpl(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
