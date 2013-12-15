package com.zchaos.zface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zchaos.zutil.BooleanUtils;

public class ZFactory {
	public static final String SWING = "swing";

	public static final String SWT = "swt";

	private static final Map<Class<?>, Class<?>> COMPONENTS_SWING = new HashMap<Class<?>, Class<?>>();

	private static final Map<Class<?>, Class<?>> COMPONENTS_SWT = new HashMap<Class<?>, Class<?>>();
	static {
		//		COMPONENTS_SWING.put(ZITable.class, ZJTable.class);
		//		COMPONENTS_SWT.put(ZITable.class, ZSTable.class);
		//
		//		COMPONENTS_SWING.put(ZIScrollPane.class, ZJScrollPane.class);
		//		COMPONENTS_SWT.put(ZIScrollPane.class, null);
		//
		//		COMPONENTS_SWING.put(ZIFrame.class, ZJFrame.class);
		//		COMPONENTS_SWT.put(ZIFrame.class, null);
	}

	/**
	 * 判断是否使用swing显示界面
	 * @return
	 */
	public static boolean useSwing() {
		String prop = System.getProperty(SWING);
		return BooleanUtils.toBoolean(prop, true);
	}

	public static boolean useSwt() {
		String prop = System.getProperty(SWT);
		return BooleanUtils.toBoolean(prop, true);
	}

	public static <T> List<T> createComponents(Class<T> clazz) {
		T jc = _createComponent(COMPONENTS_SWING, clazz);
		T sc = _createComponent(COMPONENTS_SWT, clazz);
		if (jc == null && sc == null) {
			return null;
		}
		ArrayList<T> list = new ArrayList<T>();
		if (jc != null) {
			list.add(jc);
		}
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
