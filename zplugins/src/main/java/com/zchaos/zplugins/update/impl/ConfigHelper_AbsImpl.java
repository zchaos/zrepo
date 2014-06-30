package com.zchaos.zplugins.update.impl;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;

import com.zchaos.zplugins.Constants;
import com.zchaos.zplugins.update.IConfigHelper;
import com.zchaos.zplugins.update.ILoadable;
import com.zchaos.zplugins.util.ExceptionHandler;
import com.zchaos.zplugins.util.StringUtil;
import com.zchaos.zplugins.util.Util;

/**
 * IConfigHelper的抽象实现，该类还实现了ILoadable，用于加载配置
 * <p>Copyright: Copyright (c) 2011<p>
 * <p>succez<p>
 * @author menghsh
 * @createdate 2011-10-09
 */
public abstract class ConfigHelper_AbsImpl<T> implements IConfigHelper, ILoadable<T> {

	private T configResource;

	protected volatile boolean loaded;

	protected volatile boolean synced;

	public ConfigHelper_AbsImpl() {
	}

	public boolean isLoaded() {
		return loaded;
	}

	public boolean isSynced() {
		return synced;
	}

	protected T getConfigResouce() {
		return configResource;
	}

	public IStatus load(T configResouce) {
		try {
			loaded = false;
			Assert.isNotNull(configResouce);
			configResource = configResouce;
			internalLoad();
			loaded = true;
			return Util.newStatusOk("loaded config successful!");
		}
		catch (Exception e) {
			ExceptionHandler.handleException(e);
			String message = StringUtil.joinWithSeparator(Constants.LINE_SEPARATOR, "loaded config error!", "detail:",
					e.getMessage());
			return Util.newStatusError(message);
		}
	}

	public boolean canUpdate() {
		if (!isSynced()) {
			throw new IllegalStateException("Please synchronize configuration before check updatable configuration!");
		}
		return internalCanUpdate();
	}

	protected abstract boolean internalCanUpdate();

	/**
	 * 执行加载配置操作，不抛出异常时表示加载成功
	 * @throws Exception
	 */
	protected abstract void internalLoad() throws Exception;

	public IStatus doSync() {
		try {
			synced = false;
			Assert.isLegal(isLoaded());
			internalSync();
			synced = true;
			return Util.newStatusOk("synchronize config successful!");
		}
		catch (Exception e) {
			ExceptionHandler.handleException(e);
			String message = StringUtil.joinWithSeparator(Constants.LINE_SEPARATOR, "synchronize config error!",
					"detail:", e.getMessage());
			return Util.newStatusError(message);
		}
	}

	/**
	 * 执行同步操作，不抛出异常时表示同步成功
	 * @throws Exception
	 */
	protected abstract void internalSync() throws Exception;

	public IStatus update() {
		try {
			Assert.isLegal(isLoaded() && isSynced());
			internalUpdate();
			return Util.newStatusOk("updated config successfully!");
		}
		catch (Exception e) {
			ExceptionHandler.handleException(e);
			/*
			 * 原文：更新配置时发生异常
			 * 详细信息
			 */
			String message = StringUtil.joinWithSeparator(Constants.LINE_SEPARATOR,
					"\u66f4\u65b0\u914d\u7f6e\u65f6\u53d1\u751f\u5f02\u5e38!", "\u8be6\u7ec6\u4fe1\u606f:",
					e.getMessage());
			return Util.newStatusError(message);
		}
	}

	/**
	 * 执行更新操作，不抛出异常时表示更新成功
	 * @throws Exception
	 */
	protected abstract void internalUpdate() throws Exception;

}
