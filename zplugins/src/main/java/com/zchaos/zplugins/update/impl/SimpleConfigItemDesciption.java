package com.zchaos.zplugins.update.impl;

import java.io.File;

/**
 * 描述一个简单配置的条目
 * <p>Copyright: Copyright (c) 2011<p>
 * <p>succez<p>
 * @author menghsh
 * @createdate 2011-11-15
 */
class SimpleConfigItemDesciption {
	private String description;

	private volatile boolean forceToUpdate = true;

	private String[] config;

	private String fileName;

	private String key;

	SimpleConfigItemDesciption(String[] config) {
		if (config == null || config.length < 3) {
			throw new IllegalArgumentException("Illegal argument: " + config);
		}
		String wholeKey = config[0];
		int index = wholeKey.indexOf('/');
		if (index == -1) {
			throw new IllegalArgumentException("Illegal argument: " + config);
		}
		fileName = wholeKey.substring(0, index);
		key = wholeKey.substring(index + 1);
		forceToUpdate = Boolean.parseBoolean(config[1]);
		description = config[2];
		this.config = config;
	}

	/**
	 * 该配置的描述
	 * @return
	 */
	String getDescription() {
		return description;
	}

	/**
	 * 是否强制更新
	 * @return
	 */
	boolean isForceToUpdate() {
		return forceToUpdate;
	}

	/**
	 * 获取该配置的配置信息
	 * @return
	 */
	String[] getConfig() {
		return config;
	}

	/**
	 * 获取保存保存该配置的文件
	 * @return
	 */
	File getLocation() {
		//		return new File(new File(IDEUtil.getWorkspace(), Constants.PATH_LOCAL_SIMPLE_CONFIG_FOLDER), fileName);
		return null;
	}

	/**
	 * 获取该配置的键值
	 * @return
	 */
	String getKey() {
		return key;
	}
}