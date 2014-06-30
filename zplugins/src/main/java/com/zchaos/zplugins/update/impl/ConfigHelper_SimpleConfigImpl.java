package com.zchaos.zplugins.update.impl;

import java.io.File;

import com.zchaos.zplugins.util.StringUtil;

class ConfigHelper_SimpleConfigImpl extends ConfigHelper_AbsImpl<SimpleConfigItemDesciption> {

	private SimpleConfigItemDesciption configData;

	private String syncInfo;

	public ConfigHelper_SimpleConfigImpl() {
	}

	public String getSyncInfo() {
		if (!isSynced()) {
			doSync();
		}
		return syncInfo;
	}

	protected boolean internalCanUpdate() {
		return !StringUtil.isEmpty(syncInfo);
	}

	public String getDescription() {
		return configData.getDescription();
	}

	protected void internalLoad() throws Exception {
		configData = getConfigResouce();
	}

//	private String getCurrentValue() {
//		File file = configData.getLocation();
//		String key = configData.getKey();
//		return LocalSimpleConfigs.getInstance().getValue(file.getName(), key);
//	}
//
//	private String getTargetValue() {
//		File file = configData.getLocation();
//		String key = configData.getKey();
//		return UniformSimpleConfigs.getInstance().getValue(file.getName(), key);
//	}

	protected void internalSync() throws Exception {
//		String currentValue = getCurrentValue();
//		String targetValue = getTargetValue();
//		if (StringUtils.defaultIfEmpty(currentValue, "").equalsIgnoreCase(StringUtils.defaultIfEmpty(targetValue, ""))) {
//			syncInfo = "";
//			return;
//		}
		StringBuffer buf = new StringBuffer();
//		/*
//		 * 原文：
//		 * 配置项
//		 * 当前值
//		 * 修改值
//		 */
//		buf.append("\u914d\u7f6e\u9879: ").append(configData.getDescription()).append(Constants.LINE_SEPARATOR);
//		buf.append("\u5f53\u524d\u503c: ").append(currentValue == null ? "null" : currentValue).append(
//				Constants.LINE_SEPARATOR);
//		buf.append("\u4fee\u6539\u503c: ").append(targetValue == null ? "null" : targetValue);
		syncInfo = buf.toString();
	}

	protected void internalUpdate() throws Exception {
//		String targetValue = getTargetValue();
//		LocalSimpleConfigs.getInstance().save(configData.getLocation().getName(), configData.getKey(), targetValue);
	}

	public boolean isForceToUpdate() {
		return configData.isForceToUpdate();
	}

	public String getId() {
		//		StringBuffer buf = new StringBuffer(30);
		//		buf.append(Constants.CONFIG_TYPE_SIMPLE).append('$').append(configData.getLocation().getName());
		//		buf.append('/').append(configData.getKey());
		//		return buf.toString();
		return configData.getKey();
	}
}
