package com.zchaos.zswing.internal;

import javax.swing.JTable;

import com.zchaos.ziface.ZITable;
import com.zchaos.zswing.core.ZISWINGContainer;

public class ZSWINGTable implements ZITable, ZISWINGContainer {
	private JTable table;

	public ZSWINGTable() {
		table = new JTable();
	}

	@Override
	public void setSize(int width, int height) {
		// TODO Auto-generated method stub

	}
}
