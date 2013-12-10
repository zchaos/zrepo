package com.zchaos.zwt.core;

import javax.swing.JFrame;

public class ZFrame {
	private JFrame frame;

	public ZFrame(String title) {
		frame = new JFrame(title);
	}

	public ZComponent add(ZComponent component) {
		frame.add(component.getComponent());
		return component;
	}

	public void show() {
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
