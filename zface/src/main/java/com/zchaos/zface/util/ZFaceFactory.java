package com.zchaos.zface.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zchaos.ziface.ZIFactory;
import com.zchaos.zswing.util.ZSWINGFactory;
import com.zchaos.zutil.BooleanUtils;

public class ZFaceFactory {
	public static final String SWING = "swing";

	public static final String SWT = "swt";

	private static ZIFactory FACTORY_SWING = createSWINGFactory();

	private static ZIFactory FACTORY_SWT = createSWTFactory();

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
		ArrayList<T> list = new ArrayList<T>();
		if (useSwing()) {
			T comp = createComponent(FACTORY_SWING, clazz);
			if (comp != null) {
				list.add(comp);
			}
		}
		if (useSwt()) {
			T comp = createComponent(FACTORY_SWT, clazz);
			if (comp != null) {
				list.add(comp);
			}
		}
		return list;
	}

	public static ZIFactory createSWTFactory() {
		//		Class<?> clazz = new ZSWTClassLoader().findFactoryClass();
		//		try {
		//			return (ZFactory) clazz.newInstance();
		//		}
		//		catch (InstantiationException | IllegalAccessException e) {
		//			throw new RuntimeException(e);
		//		}
		return null;
	}

	public static ZIFactory createSWINGFactory() {
		//		Class<?> clazz = new ZSWINGClassLoader().findFactoryClass();
		//		try {
		//			return (ZFactory) clazz.newInstance();
		//		}
		//		catch (InstantiationException | IllegalAccessException e) {
		//			throw new RuntimeException(e);
		//		}
		return new ZSWINGFactory();
	}

	public static <T> T createComponent(ZIFactory factory, Class<T> clazz) {
		if (factory == null) {
			return null;
		}
		Map<Class<?>, Class<?>> components = factory.getComponents();
		return createComponent(components, clazz);
	}

	@SuppressWarnings("unchecked")
	private static <T> T createComponent(Map<Class<?>, Class<?>> components, Class<T> clazz) {
		if (components == null) {
			return null;
		}
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
