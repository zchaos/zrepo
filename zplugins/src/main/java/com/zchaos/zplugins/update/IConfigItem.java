package com.zchaos.zplugins.update;

import org.eclipse.core.runtime.IStatus;

/**
 * 描述一个开发环境的配置
 * <p>Copyright: Copyright (c) 2011<p>
 * <p>succez<p>
 * @author menghsh
 * @createdate 2011-10-9
 */
public interface IConfigItem extends IConfig {

	public static final int ITEM_SIMPLE = 0;

	public static final int ITEM_PLUGIN = 1;

	/**
	 * 开发环境配置类型，不同的开发环境配置类型，其加载方式和更新方式是不同的
	 * @return
	 */
	public int getType();

	public boolean isLoaded();

	public IStatus doSync();

	/**
	 * 该配置是否强制更新
	 * @return
	 */
	public boolean isForceToUpdate();

	/**
	 * 获取该配置的id
	 * @return
	 */
	public String getId();

}
