package com.zchaos.zface.core;

import com.zchaos.zface.internal.impl.ZFrameImpl;
import com.zchaos.zface.internal.zface.ZFaceFrame;

public class ZFrame extends ZFrameImpl {

	public ZFrame() {
		ZFaceFrame frame = new ZFaceFrame();
		add(frame);
	}
}
