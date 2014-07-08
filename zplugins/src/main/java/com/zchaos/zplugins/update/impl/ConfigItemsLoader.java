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

			public IStatus update() {
				// TODO Auto-generated method stub
				return null;
			}

			public String getDescription() {
				// TODO Auto-generated method stub
				return "321";
			}

			public boolean canUpdate() {
				// TODO Auto-generated method stub
				return true;
			}

			public boolean isLoaded() {
				// TODO Auto-generated method stub
				return true;
			}

			public boolean isForceToUpdate() {
				// TODO Auto-generated method stub
				return false;
			}

			public int getType() {
				// TODO Auto-generated method stub
				return 0;
			}

			public String getId() {
				return "123";
			}

			public IStatus doSync() {
				return null;
			}
		});
		return result;
	}
}
