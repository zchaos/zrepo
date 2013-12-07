package com.zchaos.zutil.datagrid.impl;

import com.zchaos.zutil.datagrid.DataGridHeadCell;

/**
 * 数据列头单元格
 * @author zhuchx
 *
 */
public class DataGridHeadCellImpl implements DataGridHeadCell {
	private String value;

	public DataGridHeadCellImpl(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
