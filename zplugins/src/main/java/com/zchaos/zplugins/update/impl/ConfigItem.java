package com.zchaos.zplugins.update.impl;

import org.eclipse.core.runtime.IStatus;

import com.zchaos.zplugins.update.ConfigOperatorFactoryBuilder;
import com.zchaos.zplugins.update.IConfigHelper;
import com.zchaos.zplugins.update.IConfigItem;
import com.zchaos.zplugins.update.ILoadable;

public class ConfigItem implements IConfigItem {

	private int configType;

	private IConfigHelper helper;

	public ConfigItem(int type) {
		configType = type;
	}

	public IConfigHelper getHelper() {
		if (helper == null) {
			synchronized (this) {
				if (helper == null) {
					helper = ConfigOperatorFactoryBuilder.newConfigOperatorFactory().newIConfigHelper(this);
				}
			}
		}

		return helper;
	}

	public String getDescription() {
		return getHelper().getDescription();
	}

	public boolean canUpdate() {
		return getHelper().canUpdate();
	}

	public int getType() {
		return configType;
	}

	/**
	 * 是否已经加载了配置信息
	 */
	public boolean isLoaded() {
		return getHelper().isLoaded();
	}

	/**
	 * 加载配置条目信息
	 */
	public <T> void load(T configResouce) {
		IConfigHelper helper = getHelper();
		if (helper instanceof ILoadable) {
			((ILoadable) getHelper()).load(configResouce);
		}
	}

	public IStatus update() {
		return getHelper().update();
	}

	public String getSyncInfo() {
		return getHelper().getSyncInfo();
	}

	public IStatus doSync() {
		return getHelper().doSync();
	}

	public boolean isForceToUpdate() {
		return getHelper().isForceToUpdate();
	}

	public String getId() {
		return getHelper().getId();
	}

}
