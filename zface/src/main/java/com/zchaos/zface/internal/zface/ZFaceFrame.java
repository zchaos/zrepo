package com.zchaos.zface.internal.zface;

import java.util.List;

import com.zchaos.zface.internal.impl.ZFrameImpl;
import com.zchaos.zface.util.ZFaceFactory;
import com.zchaos.ziface.ZIFrame;

public class ZFaceFrame extends ZFrameImpl {

	public ZFaceFrame() {
		List<ZIFrame> frames = ZFaceFactory.createComponents(ZIFrame.class);
		add(frames);
	}
}
