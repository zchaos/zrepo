package com.zchaos.ziface;

import java.util.Map;

public interface ZIFactory {
	public Map<Class<?>, Class<?>> getComponents();
}
