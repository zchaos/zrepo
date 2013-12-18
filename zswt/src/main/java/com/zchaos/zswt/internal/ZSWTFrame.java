package com.zchaos.zswt.internal;

import org.eclipse.swt.widgets.Shell;

import com.zchaos.ziface.ZIFrame;
import com.zchaos.zswt.core.ZISWTContainer;
import com.zchaos.zswt.util.ZSWTUtils;

public class ZSWTFrame implements ZIFrame, ZISWTContainer {
	private Shell shell = null;

	public ZSWTFrame() {
		shell = new Shell(ZSWTUtils.getShell(), ZSWTUtils.getDefaultStyle());
		shell.setText("swt");
	}

	@Override
	public void setTitle(String title) {
		shell.setText(title);
	}

	@Override
	public void show() {
		shell.setVisible(true);
	}

	@Override
	public void setSize(int width, int height) {
		shell.setSize(width, height);
	}
}
