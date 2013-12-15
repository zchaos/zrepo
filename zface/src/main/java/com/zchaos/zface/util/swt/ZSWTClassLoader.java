package com.zchaos.zface.util.swt;

import com.zchaos.zface.util.ZFaceClassLoader;

public class ZSWTClassLoader extends ZFaceClassLoader {
	private static String PROJECT = "zswt";

	private static String CLASS_ZFACTORY = "com.zchaos.zswt.util.ZSWTFactory";

	protected String getProject() {
		return PROJECT;
	}

	protected String getClassZFactory() {
		return CLASS_ZFACTORY;
	}
}
