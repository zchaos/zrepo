package com.zchaos.zswing.util;

import java.util.HashMap;
import java.util.Map;

import com.zchaos.ziface.ZIFactory;
import com.zchaos.ziface.ZIFrame;
import com.zchaos.ziface.ZITable;
import com.zchaos.zswing.internal.ZSWINGFrame;
import com.zchaos.zswing.internal.ZSWINGTable;

public class ZSWINGFactory implements ZIFactory {
	private static final Map<Class<?>, Class<?>> COMPONENTS = new HashMap<Class<?>, Class<?>>();
	static {
		COMPONENTS.put(ZITable.class, ZSWINGTable.class);
		COMPONENTS.put(ZIFrame.class, ZSWINGFrame.class);
	}

	@Override
	public Map<Class<?>, Class<?>> getComponents() {
		return COMPONENTS;
	}

}
