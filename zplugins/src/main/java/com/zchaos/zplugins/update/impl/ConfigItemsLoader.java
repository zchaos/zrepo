package com.zchaos.zplugins.update.impl;

import java.io.File;

import org.eclipse.core.runtime.IStatus;

import com.zchaos.zplugins.update.IConfigItem;
import com.zchaos.zplugins.update.IConfigItems;
import com.zchaos.zplugins.update.IConfigItemsLoader;

public class ConfigItemsLoader implements IConfigItemsLoader {

	public IConfigItems load(File configResource) throws Exception {
		ConfigItems result = new ConfigItems();
		result.addConfigItem(new IConfigItem() {

			@Override
			public IStatus update() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return "321";
			}

			@Override
			public boolean canUpdate() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isLoaded() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isForceToUpdate() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public int getType() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String getId() {
				return "123";
			}

			@Override
			public IStatus doSync() {
				return null;
			}
		});
		return result;
	}
}
