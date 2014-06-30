package com.zchaos.zplugins.update;

import org.eclipse.core.runtime.IStatus;

/**
 * 描述一个可更新的配置
 * <p>Copyright: Copyright (c) 2011<p>
 * <p>succez<p>
 * @author menghsh
 * @createdate 2011-10-9
 */
public interface IConfig {

	/**
	 * 是否可以更新，以下情况可以不更新：
	 * 1.当前配置已经是最新的；2.当前配置不是必须更新的，如工程配置
	 * @return
	 */
	public boolean canUpdate();

	/**
	 * 将本配置应用到本地的开发环境
	 * @return
	 */
	public IStatus update();

	/**
	 * 获取配置描述
	 * @return
	 */
	public String getDescription();

}
