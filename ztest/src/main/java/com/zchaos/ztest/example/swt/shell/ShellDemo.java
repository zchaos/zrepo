package com.zchaos.ztest.example.swt.shell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ShellDemo {

	public static void main(String[] args) {
		new ShellDemo();
	}

	private ShellDemo() {
		Display display = new Display();
		Shell shell = new Shell(display, SWT.NO);
		shell.setLocation(300, 300);
		shell.setSize(300, 300);
		shell.setText("table小工程");
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
