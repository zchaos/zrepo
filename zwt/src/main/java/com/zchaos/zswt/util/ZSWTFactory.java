package com.zchaos.zswt.util;

import java.util.HashMap;
import java.util.Map;

import com.zchaos.zface.api.ZIFrame;
import com.zchaos.zface.api.ZITable;
import com.zchaos.zswt.internal.ZSWTFrame;
import com.zchaos.zswt.internal.ZSWTTable;

public class ZSWTFactory {

	private static final Map<Class<?>, Class<?>> COMPONENTS = new HashMap<Class<?>, Class<?>>();
	static {
		COMPONENTS.put(ZIFrame.class, ZSWTFrame.class);
		COMPONENTS.put(ZITable.class, ZSWTTable.class);
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
