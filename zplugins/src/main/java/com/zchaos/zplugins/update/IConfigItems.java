package com.zchaos.zplugins.update;

/**
 * 开发环境配置集合
 * <p>Copyright: Copyright (c) 2011<p>
 * <p>succez<p>
 * @author menghsh
 * @createdate 2011-10-9
 */
public interface IConfigItems extends IConfig {

	public IConfigItem[] getConfigItems();

	public void addConfigItem(IConfigItem item);
}
