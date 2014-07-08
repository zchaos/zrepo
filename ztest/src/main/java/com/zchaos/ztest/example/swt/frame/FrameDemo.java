package com.zchaos.ztest.example.swt.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class FrameDemo {
	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display, SWT.EMBEDDED);
		Frame frame = SWT_AWT.new_Frame(shell);

		frame.setLayout(new BorderLayout());
		frame.setBackground(Color.blue);
		frame.add(new JLabel("i'm swing label"), BorderLayout.NORTH);
		JButton btnClose = new JButton("close");
		btnClose.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				display.syncExec(new Runnable() {

					public void run() {
						shell.close();
					}
				});
			}
		});
		frame.add(btnClose, BorderLayout.SOUTH);
		Panel panel = new Panel();
		panel.setBackground(Color.orange);
		frame.add(panel, BorderLayout.CENTER);
		shell.setBounds(200, 200, 400, 300);
		shell.open();
		while (!shell.isDisposed()) {
			while (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
