package com.zchaos.ztest.example.qbookmark;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

public class QuickBookmarkAddCommandHandler extends AbstractHandler {
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("QuickBookmarkAddCommandHandler");
		return null;
	}

}
