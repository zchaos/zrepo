package com.zchaos.zswing.util;

import java.util.HashMap;
import java.util.Map;

import com.zchaos.zface.api.ZIFrame;
import com.zchaos.zface.api.ZITable;
import com.zchaos.zface.core.ZFactory;
import com.zchaos.zswing.internal.ZSWINGFrame;
import com.zchaos.zswing.internal.ZSWINGTable;

public class ZSWINGFactory extends ZFactory {
	private static final Map<Class<?>, Class<?>> COMPONENTS = new HashMap<Class<?>, Class<?>>();
	static {
		COMPONENTS.put(ZITable.class, ZSWINGTable.class);
		COMPONENTS.put(ZIFrame.class, ZSWINGFrame.class);
	}

	@Override
	protected Map<Class<?>, Class<?>> getComponents() {
		return COMPONENTS;
	}

}
