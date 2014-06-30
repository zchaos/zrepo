package com.zchaos.zplugins.update;

/**
 * 更新配置的状态
 * <p>Copyright: Copyright (c) 2012<p>
 * <p>succez<p>
 * @author menghsh
 * @createdate 2012-3-28
 */
public enum UpdateStatus {
	/**
	 * 正常
	 */
	NORMAL,

	/**
	 * 接收到请求
	 */
	REQUESTED,

	/**
	 * 正在检查更新
	 */
	CHECKUPDATE,

	/**
	 * 检查更新完毕
	 */
	CHECKEDUPDATE,

	/**
	 * 正在更新
	 */
	UPDATE,

	/**
	 * 更新完毕
	 */
	UPDATED,

	/**
	 * 出现异常
	 */
	ERROR

}
