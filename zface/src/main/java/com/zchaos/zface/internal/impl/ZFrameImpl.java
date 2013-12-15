package com.zchaos.zface.internal.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.zchaos.zface.api.ZIFrame;

public abstract class ZFrameImpl implements ZIFrame {

	protected List<ZIFrame> zframes = new ArrayList<ZIFrame>();

	public void add(ZIFrame frame) {
		zframes.add(frame);
	}

	public void add(Collection<ZIFrame> frames) {
		zframes.addAll(frames);
	}

	public List<ZIFrame> getFrames() {
		return zframes;
	}

	public void setTitle(String title) {
		List<ZIFrame> frames = getFrames();
		int size = frames == null ? 0 : frames.size();
		for (int i = 0; i < size; i++) {
			ZIFrame frame = frames.get(i);
			frame.setTitle(title);
		}
	}

	@Override
	public void show() {
		List<ZIFrame> frames = getFrames();
		int size = frames == null ? 0 : frames.size();
		for (int i = 0; i < size; i++) {
			ZIFrame frame = frames.get(i);
			frame.show();
		}
	}

	@Override
	public void setSize(int width, int height) {
		List<ZIFrame> frames = getFrames();
		int size = frames == null ? 0 : frames.size();
		for (int i = 0; i < size; i++) {
			ZIFrame frame = frames.get(i);
			frame.setSize(width, height);
		}
	}
}
