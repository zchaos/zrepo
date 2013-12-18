package com.zchaos.zswt.util;

import java.util.HashMap;
import java.util.Map;

import com.zchaos.ziface.ZIFactory;
import com.zchaos.ziface.ZIFrame;
import com.zchaos.ziface.ZITable;
import com.zchaos.zswt.internal.ZSWTFrame;
import com.zchaos.zswt.internal.ZSWTTable;

public class ZSWTFactory implements ZIFactory {

	private static final Map<Class<?>, Class<?>> COMPONENTS = new HashMap<Class<?>, Class<?>>();
	static {
		COMPONENTS.put(ZIFrame.class, ZSWTFrame.class);
		COMPONENTS.put(ZITable.class, ZSWTTable.class);
	}

	@Override
	public Map<Class<?>, Class<?>> getComponents() {
		return COMPONENTS;
	}
}
