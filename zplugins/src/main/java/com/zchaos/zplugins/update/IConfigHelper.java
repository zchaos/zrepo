package com.zchaos.zplugins.update;

import org.eclipse.core.runtime.IStatus;

/**
 * 对环境配置进行操作的接口
 * <p>Copyright: Copyright (c) 2011<p>
 * <p>succez<p>
 * @author menghsh
 * @createdate 2011-10-9
 */
public interface IConfigHelper extends IConfig {

	/**
	 * 是否已经加载了配置信息
	 * @return
	 */
	public boolean isLoaded();

	/**
	 * 是否与本地环境环境进行了同步
	 * @return
	 */
	public boolean isSynced();

	public IStatus doSync();

	/**
	 * 获取同步差异信息
	 * @return
	 */
	public String getSyncInfo();

	/**
	 * 是否必须更新该配置
	 * @return
	 */
	public boolean isForceToUpdate();

	/**
	 * 获取配置的id
	 * @return
	 */
	public String getId();

}
