package com.zchaos.ztest.example.qbookmark;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.internal.handlers.WorkbenchWindowHandlerDelegate;

public class QuickBookmarkAddActionHandler extends WorkbenchWindowHandlerDelegate {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("QuickBookmarkAddActionHandler");
		return null;
	}

}
