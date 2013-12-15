package com.zchaos.zface.internal.zface;

import java.util.List;

import com.zchaos.zface.api.ZIFrame;
import com.zchaos.zface.internal.impl.ZFrameImpl;
import com.zchaos.zface.util.ZFaceFactory;

public class ZFaceFrame extends ZFrameImpl {

	public ZFaceFrame() {
		List<ZIFrame> frames = ZFaceFactory.createComponents(ZIFrame.class);
		add(frames);
	}
}
