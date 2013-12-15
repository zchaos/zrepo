package com.zchaos.zswing.internal;

import javax.swing.JFrame;

import com.zchaos.zface.api.ZIFrame;
import com.zchaos.zswing.core.ZISWINGContainer;

public class ZSWINGFrame implements ZIFrame, ZISWINGContainer {

	private JFrame frame;

	public ZSWINGFrame() {
		frame = new JFrame();
	}

	@Override
	public void setTitle(String title) {
		frame.setTitle(title);
	}

}
