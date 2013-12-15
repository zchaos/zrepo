package com.zchaos.zface.util;

public abstract class ZFaceClassLoader extends ClassLoader {
	public Class<?> findClass(String name) throws ClassNotFoundException {
		return null;
	}

	protected abstract String getProject();

	protected abstract String getClassZFactory();
}
