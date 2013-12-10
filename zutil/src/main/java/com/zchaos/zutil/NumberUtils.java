package com.zchaos.zutil;

public class NumberUtils extends org.apache.commons.lang.math.NumberUtils {
	public static int toInt(Object obj) {
		return toInt(obj, 0);
	}

	public static int toInt(Object obj, int defaultValue) {
		if (obj == null) {
			return defaultValue;
		}
		String str = StringUtils.obj2str(obj);
		return toInt(str, defaultValue);
	}

	public static double toDouble(Object obj) {
		return toDouble(obj, 0);
	}

	public static double toDouble(Object obj, double defaultValue) {
		if (obj == null) {
			return defaultValue;
		}
		String str = StringUtils.obj2str(obj);
		return toDouble(str, defaultValue);
	}
}
