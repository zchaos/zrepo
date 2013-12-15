package com.zchaos.zface.core;

import java.util.Map;

public abstract class ZFactory {
	protected abstract Map<Class<?>, Class<?>> getComponents();

	@SuppressWarnings("unchecked")
	public <T> T createComponents(Class<T> clazz) {
		Class<?> componentClazz = getComponents().get(clazz);
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
