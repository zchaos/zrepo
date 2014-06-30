package com.zchaos.zplugins.update;

import org.eclipse.core.runtime.IStatus;

/**
 * 描述一个需要加载配置的对象
 * <p>Copyright: Copyright (c) 2011<p>
 * <p>succez<p>
 * @author menghsh
 * @createdate 2011-12-30
 */
public interface ILoadable<T> {

	/**
	 * 加载配置信息
	 */
	public IStatus load(T configResouce);

}
