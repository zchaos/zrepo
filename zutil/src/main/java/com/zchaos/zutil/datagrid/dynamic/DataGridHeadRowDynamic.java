package com.zchaos.zutil.datagrid.dynamic;

import com.zchaos.zutil.datagrid.DataGridHeadCell;

/**
 * 数据行信息
 * @author zhuchx
 *
 */
public class DataGridHeadRowDynamic extends DataGridHeadDynamic {

	public void addHeadCell(int col, DataGridHeadCell headCell) {
		addCell(col, headCell);
	}
}
