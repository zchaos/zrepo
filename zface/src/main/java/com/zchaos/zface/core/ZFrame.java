package com.zchaos.zface.core;

import com.zchaos.zface.api.ZIFrame;
import com.zchaos.zface.internal.ZFrameImpl;

public class ZFrame implements ZIFrame {
	private ZFrameImpl frame;

	public ZFrame() {
		frame = new ZFrameImpl();
	}

	@Override
	public void setTitle(String title) {
		frame.setTitle(title);
	}
}
