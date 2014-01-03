package com.zchaos.zplugins.internal.qbookmark;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.internal.handlers.WorkbenchWindowHandlerDelegate;

public class QuickAddBookmarkActionHandler extends WorkbenchWindowHandlerDelegate {
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("QuickAddBookmarkActionHandler");
		return null;
	}
}
