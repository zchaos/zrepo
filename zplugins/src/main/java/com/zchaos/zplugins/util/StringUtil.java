package com.zchaos.zplugins.util;

/**
 * 提供String的工具方法
 * <p>Copyright: Copyright (c) 2011<p>
 * <p>succez<p>
 * @author menghsh
 * @createdate 2011-10-10
 */
public class StringUtil {

	/**
	 * 将若干字符串拼接为一个字符串
	 * @param strs
	 * @return
	 */
	public static String join(String... strs) {
		StringBuffer buf = new StringBuffer(50);
		for (String s : strs) {
			buf.append(s);
		}
		return buf.toString();
	}

	/**
	 * 将若干字符串拼接为一个字符串，可以指定分隔符，分隔符由第一个字符串指定
	 * @param strs
	 * @return
	 */
	public static String joinWithSeparator(String... strs) {
		if (strs == null || strs.length < 2) {
			return "";
		}
		StringBuffer buf = new StringBuffer(50);
		String split = strs[0];
		for (int i = 1; i < strs.length - 1; i++) {
			buf.append(strs[i]).append(split);
		}
		buf.append(strs[strs.length - 1]);
		return buf.toString();
	}

	/**
	 * 获取不包含后缀名的资源名
	 * @param name
	 * @return
	 */
	public static String excludeFileExtension(String name) {
		if (isEmpty(name)) {
			return name;
		}
		int index = name.lastIndexOf('.');
		if (index == -1 || index == name.length() - 1) {
			return name;
		}
		return name.substring(0, index);
	}

	/**
	 * 获取资源名称中的后缀名,不带点号
	 * @param name
	 * @return
	 */
	public static String getFileExtension(String name) {
		if (isEmpty(name)) {
			return "";
		}
		int index = name.lastIndexOf('.');
		if (index == -1 || index == name.length() - 1) {
			return "";
		}
		return name.substring(index + 1);
	}

	/**
	 * 判断两个字符串是否相等，两个null，或两个空字符串认为是相等的
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean equals(String a, String b) {
		if (a == null && b == null || a == "" || b == "") {
			return true;
		}
		return a != null ? a.equals(b) : b.equals(a);
	}

	/**
	 * 截断字符串，使其长度不超过某个值，当字符串长度超过这个值时，截断字符串并在末尾增加...
	 * @param s
	 * @param maxLength
	 * @return
	 */
	public static String cutOffLongString(String s, int maxLength) {
		if (isEmpty(s) || maxLength < 5 || s.length() <= maxLength) {
			return s;
		}
		String result = s.substring(0, maxLength - 3);
		return result + "...";
	}
	
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
}
