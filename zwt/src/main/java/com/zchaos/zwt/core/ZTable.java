package com.zchaos.zwt.core;

import java.awt.Component;

import javax.swing.JTable;

public class ZTable extends ZComponent {
	private JTable table;

	public ZTable(Object[][] rowData, Object[] columnNames) {
		table = new JTable(rowData, columnNames);
	}

	public Component getComponent() {
		return table;
	}
}
