package com.zchaos.zwt.core;

import java.awt.Component;

import javax.swing.JScrollPane;

public class ZScrollPane extends ZComponent {
	private JScrollPane scrollPane = null;

	public ZScrollPane(ZComponent component) {
		Component comp = component.getComponent();
		scrollPane = new JScrollPane(comp);
	}

	public Component getComponent() {
		return scrollPane;
	}
}
