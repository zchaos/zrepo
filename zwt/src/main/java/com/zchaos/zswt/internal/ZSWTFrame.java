package com.zchaos.zswt.internal;

import org.eclipse.swt.widgets.Shell;

import com.zchaos.zface.api.ZIFrame;
import com.zchaos.zswt.core.ZISWTContainer;
import com.zchaos.zswt.util.ZSWTUtils;

public class ZSWTFrame implements ZIFrame, ZISWTContainer {
	private Shell shell = null;

	public ZSWTFrame() {
		shell = new Shell(ZSWTUtils.getShell(), ZSWTUtils.getDefaultStyle());
	}

	@Override
	public void setTitle(String title) {
		shell.setText(title);
	}
}
