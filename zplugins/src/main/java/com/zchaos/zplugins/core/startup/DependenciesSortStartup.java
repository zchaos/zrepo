package com.zchaos.zplugins.core.startup;

import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import com.zchaos.zplugins.internal.dependencies.sort.DependenciesSort;

public class DependenciesSortStartup implements IStartup {

	public void earlyStartup() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		workbench.getDisplay().asyncExec(new Runnable() {
			public void run() {
				DependenciesSort ds = new DependenciesSort();
				ds.sort();
			}
		});
	}
}
