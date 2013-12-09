package com.zchaos.zutil.datasource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件数据源
 * @author zhuchx
 *
 */
public class FileDataSource {
	private Class<?> clazz;

	private String path;

	public FileDataSource(String path) {
		this.path = path;
	}

	public FileDataSource(String path, Class<?> clazz) {
		this.path = path;
		this.clazz = clazz;
	}

	public InputStream getStream() throws IOException {
		if (clazz != null) {
			return clazz.getResourceAsStream(path);
		}
		return new FileInputStream(path);
	}

	public String getPath() {
		return path;
	}
}
