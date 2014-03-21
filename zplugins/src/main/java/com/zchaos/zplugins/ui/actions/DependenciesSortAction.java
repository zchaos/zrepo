package com.zchaos.zplugins.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;

import com.zchaos.zplugins.internal.dependencies.sort.DependenciesSort;

public class DependenciesSortAction extends Action {
	public DependenciesSortAction(String text) {
		super(text);
	}
	
	public void run() {
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				DependenciesSort ds = new DependenciesSort();
				ds.sort();
			}
		});
	}
}
