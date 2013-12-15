package com.zchaos.zutil;

public class BooleanUtils extends org.apache.commons.lang.BooleanUtils {
	public static boolean toBoolean(Object obj, boolean defaultValue) {
		if (obj == null) {
			return defaultValue;
		}
		if (obj instanceof Boolean) {
			Boolean b = (Boolean) obj;
			return b.booleanValue();
		}
		return toBoolean(obj.toString());
	}
}
