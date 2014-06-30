package com.zchaos.zplugins.update.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;

import com.zchaos.zplugins.Constants;
import com.zchaos.zplugins.update.IConfigItem;
import com.zchaos.zplugins.update.IConfigItems;

public class ConfigItems implements IConfigItems {

	private List<IConfigItem> items = new ArrayList<IConfigItem>();

	public boolean canUpdate() {
		for (IConfigItem item : getConfigItems()) {
			if (item.canUpdate()) {
				return true;
			}
		}
		return false;
	}

	public IConfigItem[] getConfigItems() {
		return items.toArray(new IConfigItem[items.size()]);
	}

	public void addConfigItem(IConfigItem item) {
		if (!items.contains(item)) {
			items.add(item);
		}

	}

	public IStatus update() {
		MultiStatus status = new MultiStatus(Constants.PLUGIN_ID, IStatus.OK, "", null);
		for (IConfigItem item : getConfigItems()) {
			if (!item.canUpdate()) {
				continue;
			}
			status.add(item.update());
		}
		return status;
	}

	public String getSyncInfo() {
		StringBuffer buf = new StringBuffer(500);
		for (IConfigItem item : getConfigItems()) {
			if (!item.canUpdate()) {
				continue;
			}
			buf.append(((ConfigItem) item).getSyncInfo()).append(Constants.LINE_SEPARATOR).append(
					Constants.LINE_SEPARATOR);
		}
		return buf.toString();
	}

	public String getDescription() {
		return null;
	}

	public IStatus doSync() {
		MultiStatus status = new MultiStatus(Constants.PLUGIN_ID, IStatus.OK, "", null);
		for (IConfigItem item : getConfigItems()) {
			status.add(item.doSync());
		}
		return status;
	}

}
