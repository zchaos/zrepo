package com.zchaos.zplugins.update;

import com.zchaos.zplugins.update.impl.ConfigOperatorFactory;

public class ConfigOperatorFactoryBuilder {

	public static IConfigOperatorFactory newConfigOperatorFactory() {
		return new ConfigOperatorFactory();
	}

}
