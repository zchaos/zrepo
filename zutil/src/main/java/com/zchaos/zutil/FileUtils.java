package com.zchaos.zutil;

public class FileUtils extends org.apache.commons.io.FileUtils {
	/**
	 * 返回文件的后缀名
	 * 
	 * getExt(null)=null
	 * getExt("")=null
	 * getExt("test.xls")=".xls"
	 * getExt("test")=null
	 * getExt("/env/test.xls")=".xls"
	 * getExt("/env/test")=null
	 * getExt("/env.zchaos/test")=null
	 * 
	 * @param path
	 * @return
	 */
	public static String getExt(String path) {
		if (StringUtils.isEmpty(path)) {
			return null;
		}
		int pointIndex = path.lastIndexOf('.');
		if (pointIndex < 0) {
			return null;
		}
		int slashIndex = path.lastIndexOf('/');
		if (pointIndex < slashIndex) {
			return null;
		}
		return path.substring(pointIndex);
	}
}
