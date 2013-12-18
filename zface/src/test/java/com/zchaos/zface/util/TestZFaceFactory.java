package com.zchaos.zface.util;

import java.util.List;

import com.zchaos.ziface.ZIFrame;

public class TestZFaceFactory {
	public static void main(String[] args) {
		List<ZIFrame> comps = ZFaceFactory.createComponents(ZIFrame.class);
		int size = comps == null ? 0 : comps.size();
		System.out.println(size);
	}
}
