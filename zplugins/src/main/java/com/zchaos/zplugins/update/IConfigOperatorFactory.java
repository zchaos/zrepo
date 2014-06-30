package com.zchaos.zplugins.update;

/**
 * 创建用于创建更新配置相关的对象
 * <p>Copyright: Copyright (c) 2011<p>
 * <p>succez<p>
 * @author menghsh
 * @createdate 2011-9-30
 */
public interface IConfigOperatorFactory {

	public IConfigHelper newIConfigHelper(IConfigItem item);

}
