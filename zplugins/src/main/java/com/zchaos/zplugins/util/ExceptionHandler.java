package com.zchaos.zplugins.util;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;

import com.zchaos.zplugins.Constants;

/**
 * 异常处理类
 * <p>Copyright: Copyright (c) 2011<p>
 * <p>succez<p>
 * @author menghsh
 * @createdate 2011-9-29
 */
public class ExceptionHandler {

	/**
	 * 处理异常，不重新抛出
	 * @param t
	 */
	public static void handleException(Throwable t) {
		Logger.log(t.getMessage(), t);
	}

	/**
	 * 检查状态信息，假如状态不正常，抛出异常
	 * @param status
	 * @throws Throwable
	 */
	public static void throwException(IStatus status) throws Throwable {
		Assert.isNotNull(status);
		if (status.isOK()) {
			return;
		}
		Throwable t = status.getException();
		if (t != null) {
			throw t;
		}
		throw new Exception(status.getMessage());
	}

	/**
	 * 检查状态信息，假如状态不正常，抛出运行时异常
	 * @param status
	 * @throws Throwable
	 */
	public static void throwRuntimeException(IStatus status) {
		Assert.isNotNull(status);
		if (status.isOK()) {
			return;
		}
		Throwable t = status.getException();
		if (t != null) {
			if (t instanceof RuntimeException) {
				throw (RuntimeException) t;
			}
			throw new RuntimeException(t.getMessage(), t);
		}
		throw new RuntimeException(status.getMessage());
	}

	/**
	 * 将异常包装为运行时异常，并将之抛出
	 * @param t
	 */
	public static void throwRuntimeException(Throwable t) {
		Assert.isNotNull(t);
		if (t instanceof RuntimeException) {
			throw (RuntimeException) t;
		}
		throw new RuntimeException(t.getMessage(), t);
	}

	/**
	 * 获取异常兑现的堆栈
	 * @param t
	 * @return
	 */
	public static String getStackTrace(Throwable t) {
		if (t == null) {
			return null;
		}
		StringBuffer buf = new StringBuffer(500);
		buf.append(t).append(Constants.LINE_SEPARATOR);
		StackTraceElement[] traces = t.getStackTrace();
		for (StackTraceElement trace : traces) {
			buf.append("\tat ").append(trace).append(Constants.LINE_SEPARATOR);
		}
		Throwable cause = t.getCause();
		if (cause != null && !cause.equals(t)) {
			getStackTraceAsCause(cause, traces, buf);
		}
		return buf.toString();
	}

	private static void getStackTraceAsCause(Throwable t, StackTraceElement[] causedTrace, StringBuffer buf) {
		StackTraceElement[] trace = t.getStackTrace();
		int m = trace.length - 1, n = causedTrace.length - 1;
		while (m >= 0 && n >= 0 && trace[m].equals(causedTrace[n])) {
			m--;
			n--;
		}
		int framesInCommon = trace.length - 1 - m;
		buf.append("Caused by: " + t).append(Constants.LINE_SEPARATOR);

		for (int i = 0; i <= m; i++)
			buf.append("\tat " + trace[i]).append(Constants.LINE_SEPARATOR);
		if (framesInCommon != 0)
			buf.append("\t... " + framesInCommon + " more").append(Constants.LINE_SEPARATOR);

		Throwable ourCause = t.getCause();
		if (ourCause != null) {
			getStackTraceAsCause(ourCause, trace, buf);
		}
	}

}
