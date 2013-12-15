package com.zchaos.zswt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zchaos.zutil.BooleanUtils;

public class ZFactory {
	public static final String SWT = "swt";

	private static final Map<Class<?>, Class<?>> COMPONENTS_SWT = new HashMap<Class<?>, Class<?>>();
	static {
		//		COMPONENTS_SWT.put(ZITable.class, ZSTable.class);
		//
		//		COMPONENTS_SWT.put(ZIScrollPane.class, null);
		//
		//		COMPONENTS_SWT.put(ZIFrame.class, null);
	}

	public static boolean useSwt() {
		String prop = System.getProperty(SWT);
		return BooleanUtils.toBoolean(prop, true);
	}

	public static <T> List<T> createComponents(Class<T> clazz) {
		T sc = _createComponent(COMPONENTS_SWT, clazz);
		if (sc == null) {
			return null;
		}
		ArrayList<T> list = new ArrayList<T>();
		if (sc != null) {
			list.add(sc);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	private static <T> T _createComponent(Map<Class<?>, Class<?>> components, Class<T> clazz) {
		Class<?> componentClazz = components.get(clazz);
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
