package com.zchaos.zutil;

public class StringUtils extends org.apache.commons.lang.StringUtils {
	public static String obj2str(Object obj) {
		return obj2str(obj, null);
	}

	public static String obj2str(Object obj, String defaultValue) {
		if (obj == null) {
			return defaultValue;
		}
		return obj.toString();
	}
}
