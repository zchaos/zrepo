package com.zchaos.zswing.internal;

import javax.swing.JFrame;

import com.zchaos.zface.api.ZIFrame;
import com.zchaos.zswing.core.ZISWINGContainer;

public class ZSWINGFrame implements ZIFrame, ZISWINGContainer {

	private JFrame frame;

	public ZSWINGFrame() {
		frame = new JFrame();
		frame.setTitle("swing");
	}

	@Override
	public void setTitle(String title) {
		frame.setTitle(title);
	}

	@Override
	public void show() {
		frame.setVisible(true);
	}

	@Override
	public void setSize(int width, int height) {
		frame.setSize(width, height);
	}
}
