package com.zchaos.zplugins.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;

import com.zchaos.zplugins.Constants;

public class Util {

	public static IStatus newStatusOk(String message) {
		return new Status(IStatus.OK, Constants.PLUGIN_ID, StringUtil.join(message));
	}

	public static IStatus newStatusError(String message) {
		return new Status(IStatus.ERROR, Constants.PLUGIN_ID, StringUtil.join(message));
	}

	public static IStatus newStatusError(Throwable t) {
		return new Status(IStatus.ERROR, Constants.PLUGIN_ID, 1, t.getMessage(), t);
	}

	public static int stmTryCopyFrom(InputStream in, OutputStream out) throws IOException {
		byte[] buf = new byte[1024];
		int sz = 0;
		int r;
		while ((r = in.read(buf)) != -1) {
			sz += r;
			out.write(buf, 0, r);
		}
		return sz;
	}

	public static void safeClose(Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			}
			catch (IOException e) {
			}
		}
	}

	/**
	 * Load content of file under <code>url</code> displaying progress on given
	 * context.
	 * 
	 * 拷贝自{@link org.eclipse.compare.internal.Utilities}
	 * @param url
	 * @param context
	 * @return the content of file under given URL, or <code>null</code> if URL
	 *         could not be loaded
	 * @throws InvocationTargetException
	 *             thrown on errors while URL loading
	 * @throws OperationCanceledException
	 * @throws InterruptedException
	 * @see org.eclipse.compare.internal.Utilities#getURLContents
	 */
	public static String getURLContents(final URL url, IRunnableContext context) throws InvocationTargetException,
			OperationCanceledException, InterruptedException {
		final String[] result = new String[1];
		context.run(true, true, new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				SubMonitor progress = SubMonitor.convert(monitor, "Opening connection to the URL", 100);
				try {
					URLConnection connection = url.openConnection();
					progress.worked(10);
					if (monitor.isCanceled())
						throw new OperationCanceledException();
					setReadTimeout(connection, 60 * 1000);
					progress.setTaskName("Fetching content from the URL");
					String enc = connection.getContentEncoding();
					if (enc == null)
						enc = ResourcesPlugin.getEncoding();
					result[0] = Util.readString(connection.getInputStream(), enc, connection.getContentLength(),
							progress.newChild(90));
				}
				catch (SocketTimeoutException e) {
					e.printStackTrace();
					throw new InvocationTargetException(e);
				}
				catch (IOException e) {
					e.printStackTrace();
					throw new InvocationTargetException(e);
				}
				finally {
					monitor.done();
				}
			}
		});
		return result[0];
	}

	/**
	 * 设置超时时间
	 * 拷贝自{@link org.eclipse.compare.internal.Utilities}
	 * 
	 * @param connection a connection for which the timeout is set
	 * @param timeout an int that specifies the connect timeout value in milliseconds
	 * @return whether the timeout has been successfully set
	 * 
	 * @see org.eclipse.compare.internal.Utilities#setReadTimeout
	 */
	public static boolean setReadTimeout(URLConnection connection, int timeout) {
		Method[] methods = connection.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals("setReadTimeout")) //$NON-NLS-1$
				try {
					methods[i].invoke(connection, new Object[] { new Integer(timeout) });
					return true;
				}
				catch (IllegalArgumentException e) { // ignore
				}
				catch (IllegalAccessException e) { // ignore
				}
				catch (InvocationTargetException e) { // ignore
				}
		}
		return false;
	}

	/**
	 * 拷贝自{@link org.eclipse.compare.internal.Utilities}
	 * @param is
	 * @param encoding
	 * @return
	 * @throws IOException
	 * 
	 * @see org.eclipse.compare.internal.Utilities#setReadTimeout
	 */
	public static String readString(InputStream is, String encoding) throws IOException {
		return readString(is, encoding, -1, null);
	}

	/**
	 * 拷贝自{@link org.eclipse.compare.internal.Utilities}
	 * @param is
	 * @param encoding
	 * @param length
	 * @param monitor
	 * @return
	 * @throws IOException
	 * 
	 * @see org.eclipse.compare.internal.Utilities#setReadTimeout
	 */
	public static String readString(InputStream is, String encoding, int length, IProgressMonitor monitor)
			throws IOException {
		SubMonitor progress = SubMonitor.convert(monitor);
		progress.setWorkRemaining(length);
		if (is == null)
			return null;
		BufferedReader reader = null;
		try {
			StringBuffer buffer = new StringBuffer();
			char[] part = new char[2048];
			int read = 0;
			reader = new BufferedReader(new InputStreamReader(is, encoding));
			while ((read = reader.read(part)) != -1) {
				buffer.append(part, 0, read);
				progress.worked(2048);
				if (progress.isCanceled())
					throw new OperationCanceledException();
			}

			return buffer.toString();
		}
		finally {
			if (reader != null) {
				try {
					reader.close();
				}
				catch (IOException ex) {
					// silently ignored
				}
			}
		}
	}

}
