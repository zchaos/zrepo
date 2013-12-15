package com.zchaos.zswing.util;

import java.util.HashMap;
import java.util.Map;

import com.zchaos.zface.api.ZIFrame;
import com.zchaos.zface.api.ZITable;
import com.zchaos.zswing.internal.ZSWINGFrame;
import com.zchaos.zswing.internal.ZSWINGTable;

public class ZSWINGFactory {
	private static final Map<Class<?>, Class<?>> COMPONENTS = new HashMap<Class<?>, Class<?>>();
	static {
		COMPONENTS.put(ZITable.class, ZSWINGTable.class);
		COMPONENTS.put(ZIFrame.class, ZSWINGFrame.class);
	}

	@SuppressWarnings("unchecked")
	public static <T> T createComponents(Class<T> clazz) {
		Class<?> componentClazz = COMPONENTS.get(clazz);
		if (componentClazz == null) {
			return null;
		}
		try {
			return (T) componentClazz.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
