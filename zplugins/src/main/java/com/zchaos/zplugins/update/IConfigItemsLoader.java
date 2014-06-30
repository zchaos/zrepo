package com.zchaos.zplugins.update;

import java.io.File;

/**
 * 加载配置条目
 * <p>Copyright: Copyright (c) 2011<p>
 * <p>succez<p>
 * @author menghsh
 * @createdate 2011-10-9
 */
public interface IConfigItemsLoader {

	public IConfigItems load(File configResource) throws Exception;

}
