package com.zchaos.zswt.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ZSWTUtils {
	private static Display display = Display.getDefault();

	private static Shell shell = new Shell(display);

	public static Display getDisplay() {
		return display;
	}

	public static Shell getShell() {
		return shell;
	}

	public static int getDefaultStyle() {
		return SWT.SHELL_TRIM;
	}
}
