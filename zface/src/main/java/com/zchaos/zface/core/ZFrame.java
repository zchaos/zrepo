package com.zchaos.zface.core;

import com.zchaos.zface.internal.ZFrameImpl;

public class ZFrame implements ZContainer {
	private ZFrameImpl frame;

	public ZFrame(String title) {
		frame = new ZFrameImpl();
	}
}
