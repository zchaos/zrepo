package com.zchaos.zutil.datagrid.dynamic;

import com.zchaos.zutil.datagrid.DataGridHeadCell;

/**
 * 数据列信息
 * @author zhuchx
 *
 */
public class DataGridHeadColDynamic extends DataGridHeadDynamic {

	public void addHeadCell(int col, DataGridHeadCell headCell) {
		addCell(col, headCell);
	}
}
