package com.zchaos.zplugins.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

import com.zchaos.zplugins.Constants;
import com.zchaos.zplugins.MyPlugin;

/**
 * 记录日志
 * 由{@link org.eclipse.ui.internal.WorkbenchPlugin}抽取而来，插件无法调用WorkbenchPlugin的方法
 * <p>Copyright: Copyright (c) 2011<p>
 * <p>succez<p>
 * @author menghsh
 * @createdate 2011-9-29
 */
@SuppressWarnings("restriction")
public class Logger {

	/**
	 * Logs the given message to the platform log.
	 * 
	 * If you have an exception in hand, call log(String, Throwable) instead.
	 * 
	 * If you have a status object in hand call log(String, IStatus) instead.
	 * 
	 * This convenience method is for internal use by the Workbench only and
	 * must not be called outside the Workbench.
	 * 
	 * @param message
	 *            A high level UI message describing when the problem happened.
	 * @see org.eclipse.ui.internal.WorkbenchPlugin#log(String)
	 */
	public static void log(String message) {
		getLog().log(newStatus(IStatus.ERROR, message, null));
	}

	/**
	 * Log the throwable.
	 * @param t
	 * @see org.eclipse.ui.internal.WorkbenchPlugin#log(Throwable)
	 */
	public static void log(Throwable t) {
		getLog().log(getStatus(t));
	}

	/**
	 * Return the status from throwable
	 * @param t throwable
	 * @return IStatus
	 * @see org.eclipse.ui.internal.WorkbenchPlugin#getStatus(Throwable)
	 */
	private static IStatus getStatus(Throwable t) {
		String message = getLocalizedMessage(t);

		return newError(message, t);
	}

	/**
	 * Create a new error from the message and the
	 * throwable.
	 * @param message
	 * @param t
	 * @return IStatus
	 * @see org.eclipse.ui.internal.WorkbenchPlugin#newError(String, Throwable)
	 */
	public static IStatus newError(String message, Throwable t) {
		String pluginId = Constants.PLUGIN_ID;
		int errorCode = IStatus.OK;

		if (t instanceof CoreException) {
			CoreException ce = (CoreException) t;
			pluginId = ce.getStatus().getPlugin();
			errorCode = ce.getStatus().getCode();
		}

		return new Status(IStatus.ERROR, pluginId, errorCode, message, getCause(t));
	}

	/**
	 * Returns a localized message describing the given exception. If the given exception does not
	 * have a localized message, this returns the string "An error occurred".
	 *
	 * @param exception
	 * @return
	 * @see org.eclipse.ui.internal.misc.StatusUtil#getLocalizedMessage(Throwable)
	 */
	public static String getLocalizedMessage(Throwable exception) {
		String message = exception.getLocalizedMessage();

		if (message != null) {
			return message;
		}

		if (exception instanceof CoreException) {
			CoreException ce = (CoreException) exception;
			return ce.getStatus().getMessage();
		}

		return "An unexpected exception was thrown.";
	}

	/**
	 * Logs the given message and throwable to the platform log.
	 * 
	 * If you have a status object in hand call log(String, IStatus) instead.
	 * 
	 * This convenience method is for internal use by the Workbench only and
	 * must not be called outside the Workbench.
	 * 
	 * @param message
	 *            A high level UI message describing when the problem happened.
	 * @param t
	 *            The throwable from where the problem actually occurred.
	 * @see org.eclipse.ui.internal.WorkbenchPlugin#log(String, Throwable)
	 */
	public static void log(String message, Throwable t) {
		IStatus status = newStatus(IStatus.ERROR, message, t);
		log(message, status);
	}

	public static void log(@SuppressWarnings("rawtypes") Class clazz, String methodName, Throwable t) {
	}

	public static void log(String message, IStatus status) {
		if (message != null) {
			getLog().log(newStatus(IStatus.ERROR, message, null));
		}

		getLog().log(status);
	}

	/**
	 * Log the status to the default log.
	 * @param status
	 * @see org.eclipse.ui.internal.WorkbenchPlugin#log(IStatus)
	 */
	public static void log(IStatus status) {
		getLog().log(status);
	}

	public static ILog getLog() {
		Assert.isLegal(MyPlugin.isStarted());
		return Platform.getLog(MyPlugin.getDefault().getBundle());
	}

	/**
	 * Create a new error from the message and the
	 * throwable.
	 * @param message
	 * @param t
	 * @return IStatus
	 * @see org.eclipse.ui.internal.misc.StatusUtil#newStatus(int, String, Throwable)
	 */
	public static IStatus newStatus(int severity, String message, Throwable exception) {

		String statusMessage = message;
		if (message == null || message.trim().length() == 0) {
			if (exception.getMessage() == null) {
				statusMessage = exception.toString();
			}
			else {
				statusMessage = exception.getMessage();
			}
		}

		return new Status(severity, Constants.PLUGIN_ID, severity, statusMessage, getCause(exception));
	}

	/**
	 * @param exception
	 * @return
	 * @see org.eclipse.ui.internal.misc.StatusUtil#getCause(Throwable)
	 */
	public static Throwable getCause(Throwable exception) {
		Throwable cause = null;
		if (exception != null) {
			if (exception instanceof CoreException) {
				CoreException ce = (CoreException) exception;
				cause = ce.getStatus().getException();
			}
			else {
				try {
					Method causeMethod = exception.getClass().getMethod("getCause", new Class[0]); //$NON-NLS-1$
					Object o = causeMethod.invoke(exception, new Object[0]);
					if (o instanceof Throwable) {
						cause = (Throwable) o;
					}
				}
				catch (NoSuchMethodException e) {
				}
				catch (IllegalArgumentException e) {
				}
				catch (IllegalAccessException e) {
				}
				catch (InvocationTargetException e) {
				}
			}

			if (cause == null) {
				cause = exception;
			}
		}

		return cause;
	}
}
