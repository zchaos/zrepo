package com.zchaos.zplugins.update.impl;

import com.zchaos.zplugins.update.IConfigHelper;
import com.zchaos.zplugins.update.IConfigItem;
import com.zchaos.zplugins.update.IConfigOperatorFactory;

public class ConfigOperatorFactory implements IConfigOperatorFactory {

	public IConfigHelper newIConfigHelper(IConfigItem item) {
		return new ConfigHelper_SimpleConfigImpl();
	}

}
