package com.zchaos.zswt.internal;

import org.eclipse.swt.widgets.Table;

import com.zchaos.ziface.ZITable;
import com.zchaos.zswt.core.ZISWTContainer;
import com.zchaos.zswt.util.ZSWTUtils;

public class ZSWTTable implements ZITable, ZISWTContainer {
	private Table table = null;

	public ZSWTTable() {
		table = new Table(ZSWTUtils.getShell(), ZSWTUtils.getDefaultStyle());
	}

	@Override
	public void setSize(int width, int height) {
		// TODO Auto-generated method stub

	}
}
