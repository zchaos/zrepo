package com.zchaos.zplugins.ui.dialogs;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IconAndMessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.zchaos.zplugins.util.ExceptionHandler;

/**
 * 异常信息对话框
 * <p>Copyright: Copyright (c) 2011<p>
 * <p>succez<p>
 * @author menghsh
 * @createdate 2011-10-12
 */
public class ErrorDialog extends IconAndMessageDialog {

	/*
	 * 原因：出错啦
	 */
	public static final String DEFAULT_TITLE = "\u51fa\u9519\u5566";

	/**
	 * Static to prevent opening of error dialogs for automated testing.
	 */
	public static boolean AUTOMATED_MODE = false;

	private Button detailsButton;

	private String title;

	private boolean stackTraceTextCreated = false;

	/**
	 * Filter mask for determining which status items to display.
	 */
	private int displayMask = 0xFFFF;

	private IStatus status;

	private Text stackTraceText;

	private Label detailLabel;

	public ErrorDialog(Shell parentShell, String dialogTitle, String message, IStatus status, int displayMask) {
		super(parentShell);
		this.title = dialogTitle == null ? JFaceResources.getString("Problem_Occurred") : //$NON-NLS-1$
				dialogTitle;
		this.message = "";
		if (!isEmpty(message)) {
			this.message = message;
		}
		else if (status != null) {
			String msg = status.getMessage();
			if (isEmpty(msg)) {
				Throwable t = status.getException();
				if (t != null) {
					msg = t.getMessage();
					if (isEmpty(msg)) {
						msg = t.toString();
					}
					this.message = msg;
				}
			}
		}

		this.status = status;
		this.displayMask = displayMask;
	}
	
	private boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	protected void buttonPressed(int id) {
		if (id == IDialogConstants.DETAILS_ID) {
			toggleDetailsArea();
		}
		else {
			super.buttonPressed(id);
		}
	}

	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(title);
	}

	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createDetailsButton(parent);
	}

	private void createDetailsButton(Composite parent) {
		if (shouldShowDetailsButton()) {
			detailsButton = createButton(parent, IDialogConstants.DETAILS_ID, IDialogConstants.SHOW_DETAILS_LABEL,
					false);
		}
	}

	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		createMessageArea(composite);
		GridLayout layout = new GridLayout();
		layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		layout.numColumns = 2;
		composite.setLayout(layout);
		GridData childData = new GridData(GridData.FILL_BOTH);
		childData.horizontalSpan = 2;
		childData.grabExcessVerticalSpace = false;
		composite.setLayoutData(childData);
		composite.setFont(parent.getFont());

		return composite;
	}

	/*
	 * @see IconAndMessageDialog#createDialogAndButtonArea(Composite)
	 */
	protected void createDialogAndButtonArea(Composite parent) {
		super.createDialogAndButtonArea(parent);
		if (this.dialogArea instanceof Composite) {
			// Create a label if there are no children to force a smaller layout
			Composite dialogComposite = (Composite) dialogArea;
			if (dialogComposite.getChildren().length == 0) {
				new Label(dialogComposite, SWT.NULL);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.IconAndMessageDialog#getImage()
	 */
	protected Image getImage() {
		if (status != null) {
			if (status.getSeverity() == IStatus.WARNING) {
				return getWarningImage();
			}
			if (status.getSeverity() == IStatus.INFO) {
				return getInfoImage();
			}
		}
		return getErrorImage();
	}

	private void createStackSection(Composite parent) {
		Composite container = new Composite(parent, 0);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 6;
		container.setLayout(layout);
		GridData gd = new GridData(1808);
		gd.heightHint = 100;
		container.setLayoutData(gd);

		detailLabel = new Label(container, 0);
		detailLabel.setText("Exception Stack Trace");
		gd = new GridData();
		gd.verticalAlignment = 1024;
		detailLabel.setLayoutData(gd);

		this.stackTraceText = new Text(container, 2818);
		gd = new GridData(1808);
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 2;
		this.stackTraceText.setLayoutData(gd);
		this.stackTraceText.setEditable(false);
		stackTraceTextCreated = true;
		if (status != null && status.getException() != null) {
			stackTraceText.setText(ExceptionHandler.getStackTrace(status.getException()));
		}
	}

	/*
	 * (non-Javadoc) Method declared on Window.
	 */
	/**
	 * Extends <code>Window.open()</code>. Opens an error dialog to display
	 * the error. If you specified a mask to filter the displaying of these
	 * children, the error dialog will only be displayed if there is at least
	 * one child status matching the mask.
	 */
	public int open() {
		if (!AUTOMATED_MODE && shouldDisplay(status, displayMask)) {
			return super.open();
		}
		setReturnCode(OK);
		return OK;
	}

	/**
	 * Opens an error dialog to display the given error. Use this method if the
	 * error object being displayed does not contain child items, or if you wish
	 * to display all such items without filtering.
	 * 
	 * @param parent
	 *            the parent shell of the dialog, or <code>null</code> if none
	 * @param dialogTitle
	 *            the title to use for this dialog, or <code>null</code> to
	 *            indicate that the default title should be used
	 * @param message
	 *            the message to show in this dialog, or <code>null</code> to
	 *            indicate that the error's message should be shown as the
	 *            primary message
	 * @param status
	 *            the error to show to the user
	 * @return the code of the button that was pressed that resulted in this
	 *         dialog closing. This will be <code>Dialog.OK</code> if the OK
	 *         button was pressed, or <code>Dialog.CANCEL</code> if this
	 *         dialog's close window decoration or the ESC key was used.
	 */
	public static int openError(Shell parent, String dialogTitle, String message, IStatus status) {
		return openError(parent, dialogTitle, message, status, IStatus.OK | IStatus.INFO | IStatus.WARNING
				| IStatus.ERROR);
	}

	/**
	 * Opens an error dialog to display the given error. Use this method if the
	 * error object being displayed contains child items <it>and </it> you wish
	 * to specify a mask which will be used to filter the displaying of these
	 * children. The error dialog will only be displayed if there is at least
	 * one child status matching the mask.
	 * 
	 * @param parentShell
	 *            the parent shell of the dialog, or <code>null</code> if none
	 * @param title
	 *            the title to use for this dialog, or <code>null</code> to
	 *            indicate that the default title should be used
	 * @param message
	 *            the message to show in this dialog, or <code>null</code> to
	 *            indicate that the error's message should be shown as the
	 *            primary message
	 * @param status
	 *            the error to show to the user
	 * @param displayMask
	 *            the mask to use to filter the displaying of child items, as
	 *            per <code>IStatus.matches</code>
	 * @return the code of the button that was pressed that resulted in this
	 *         dialog closing. This will be <code>Dialog.OK</code> if the OK
	 *         button was pressed, or <code>Dialog.CANCEL</code> if this
	 *         dialog's close window decoration or the ESC key was used.
	 * @see org.eclipse.core.runtime.IStatus#matches(int)
	 */
	public static int openError(Shell parentShell, String title, String message, IStatus status, int displayMask) {
		ErrorDialog dialog = new ErrorDialog(parentShell, title, message, status, displayMask);
		return dialog.open();
	}

	/**
	 * Returns whether the given status object should be displayed.
	 * 
	 * @param status
	 *            a status object
	 * @param mask
	 *            a mask as per <code>IStatus.matches</code>
	 * @return <code>true</code> if the given status should be displayed, and
	 *         <code>false</code> otherwise
	 * @see org.eclipse.core.runtime.IStatus#matches(int)
	 */
	protected static boolean shouldDisplay(IStatus status, int mask) {
		IStatus[] children = status.getChildren();
		if (children == null || children.length == 0) {
			return status.matches(mask);
		}
		for (int i = 0; i < children.length; i++) {
			if (children[i].matches(mask)) {
				return true;
			}
		}
		return false;
	}

	private void toggleDetailsArea() {
		//		Point windowSize = getShell().getSize();
		//		Point oldSize = getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT);
		if (stackTraceTextCreated) {
			detailLabel.setVisible(!detailLabel.isVisible());
			stackTraceText.setVisible(!stackTraceText.isVisible());
			//			detailLabel.dispose();
			//			stackTraceText.dispose();
			detailsButton.setText(IDialogConstants.SHOW_DETAILS_LABEL);
			getContents().getShell().layout();
			getShell().setSize(new Point(600, detailLabel.isVisible() ? 400 : 200));
		}
		else {
			createStackSection((Composite) getContents());
			detailsButton.setText(IDialogConstants.HIDE_DETAILS_LABEL);
			getContents().getShell().layout();
			getShell().setSize(new Point(600, 400));
		}
		//		Point newSize = getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT);
		//		getShell().setSize(new Point(newSize.x, newSize.y));
	}

	/**
	 * Show the details portion of the dialog if it is not already visible. This
	 * method will only work when it is invoked after the control of the dialog
	 * has been set. In other words, after the <code>createContents</code>
	 * method has been invoked and has returned the control for the content area
	 * of the dialog. Invoking the method before the content area has been set
	 * or after the dialog has been disposed will have no effect.
	 * 
	 * @since 3.1
	 */
	protected final void showDetailsArea() {
		if (!stackTraceTextCreated) {
			Control control = getContents();
			if (control != null && !control.isDisposed()) {
				toggleDetailsArea();
			}
		}
	}

	public boolean shouldShowDetailsButton() {
		return true;
	}

	public final void setStatus(IStatus status) {
		if (this.status != status) {
			this.status = status;
		}
	}

	protected boolean isResizable() {
		return true;
	}

}
