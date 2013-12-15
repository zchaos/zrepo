package com.zchaos.zface.util.swing;

import com.zchaos.zface.util.ZFaceClassLoader;

public class ZSWINGClassLoader extends ZFaceClassLoader {
	private static String PROJECT = "zswing";

	private static String CLASS_ZFACTORY = "com.zchaos.zswing.util.ZSWINGFactory";

	protected String getProject() {
		return PROJECT;
	}

	protected String getClassZFactory() {
		return CLASS_ZFACTORY;
	}
}
