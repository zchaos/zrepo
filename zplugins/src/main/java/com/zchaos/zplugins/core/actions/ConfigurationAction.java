package com.zchaos.zplugins.core.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class ConfigurationAction extends Action {
	public ConfigurationAction(String text) {
		super(text);
	}
	
	public void run() {
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				final Shell shell = new Shell(PlatformUI.getWorkbench().getDisplay());
				MessageDialog waitDialg = new MessageDialog(shell, "aaaaaaa", null, "bbbbbb", MessageDialog.INFORMATION,
						new String[] { IDialogConstants.OK_LABEL }, 0);
				waitDialg.setBlockOnOpen(false);
				waitDialg.open();
			}
		});
	}
}
