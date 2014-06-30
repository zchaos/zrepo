package com.zchaos.zplugins;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class MyPlugin extends AbstractUIPlugin {

	private static MyPlugin plugin;

	public MyPlugin() {
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static MyPlugin getDefault() {
		return plugin;
	}

	/**
	 * 本插件是否已经运行
	 * @return
	 */
	public static boolean isStarted() {
		return plugin != null;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(Constants.PLUGIN_ID, path);
	}
}
