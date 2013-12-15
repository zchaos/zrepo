package com.zchaos.zswt.util;

import java.util.HashMap;
import java.util.Map;

import com.zchaos.zface.api.ZIFrame;
import com.zchaos.zface.api.ZITable;
import com.zchaos.zface.core.ZFactory;
import com.zchaos.zswt.internal.ZSWTFrame;
import com.zchaos.zswt.internal.ZSWTTable;

public class ZSWTFactory extends ZFactory {

	private static final Map<Class<?>, Class<?>> COMPONENTS = new HashMap<Class<?>, Class<?>>();
	static {
		COMPONENTS.put(ZIFrame.class, ZSWTFrame.class);
		COMPONENTS.put(ZITable.class, ZSWTTable.class);
	}

	@Override
	protected Map<Class<?>, Class<?>> getComponents() {
		return COMPONENTS;
	}
}
