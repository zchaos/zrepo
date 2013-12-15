package com.zchaos.zface.util;

import java.util.ArrayList;
import java.util.List;

import com.zchaos.zface.core.ZFactory;
import com.zchaos.zface.util.swing.ZSWINGClassLoader;
import com.zchaos.zface.util.swt.ZSWTClassLoader;
import com.zchaos.zutil.BooleanUtils;

public class ZFaceFactory {
	public static final String SWING = "swing";

	public static final String SWT = "swt";

	private static ZFactory FACTORY_SWING = createSWINGFactory();

	private static ZFactory FACTORY_SWT = createSWTFactory();

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
			T comp = createSWINGComponent(clazz);
			if (comp != null) {
				list.add(comp);
			}
		}
		if (useSwt()) {
			T comp = createSWINGComponent(clazz);
			if (comp != null) {
				list.add(comp);
			}
		}
		return list;
	}

	public static <T> T createSWTComponent(Class<T> clazz) {
		return FACTORY_SWT.createComponents(clazz);
	}

	public static <T> T createSWINGComponent(Class<T> clazz) {
		return FACTORY_SWING.createComponents(clazz);
	}

	public static ZFactory createSWTFactory() {
		Class<?> clazz = new ZSWTClassLoader().findFactoryClass();
		try {
			return (ZFactory) clazz.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static ZFactory createSWINGFactory() {
		Class<?> clazz = new ZSWINGClassLoader().findFactoryClass();
		try {
			return (ZFactory) clazz.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
