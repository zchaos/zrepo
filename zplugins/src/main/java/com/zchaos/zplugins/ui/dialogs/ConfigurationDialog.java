package com.zchaos.zplugins.ui.dialogs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.zchaos.zplugins.Constants;
import com.zchaos.zplugins.update.IConfigItem;
import com.zchaos.zplugins.update.IConfigItems;
import com.zchaos.zplugins.update.impl.ConfigItemsLoader;
import com.zchaos.zplugins.util.ExceptionHandler;

public class ConfigurationDialog extends TitleAreaDialog {

	public ConfigurationDialog(Shell shell) {
		super(shell);
	}

	private Table configsTable;

	private Button selectAllButton;

	private Button deselectAllButton;

	private Button ignoreButton;

	private Button showAllButton;

	protected Control createDialogArea(Composite parent) {
		final SashForm advancedComposite = new SashForm(parent, SWT.VERTICAL);
		GridData sashData = new GridData(SWT.FILL, SWT.FILL, true, true);
		advancedComposite.setLayoutData(sashData);

		Composite mainColumn = new Composite(advancedComposite, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		mainColumn.setFont(parent.getFont());
		mainColumn.setLayout(layout);

		GridData data = new GridData(GridData.BEGINNING);
		data.horizontalSpan = 2;

		createConfigurationViewer(mainColumn);

		createOperationControl(mainColumn);

		updateButtons();

		return advancedComposite;
	}

	private Button createButton(Composite parent, String label) {
		Button button = new Button(parent, SWT.PUSH | SWT.CENTER);
		button.setText(label);
		setButtonLayoutData(button);
		return button;
	}

	private void createConfigurationViewer(Composite parent) {
		configsTable = new Table(parent, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
		GridData data = new GridData(GridData.FILL_BOTH | GridData.VERTICAL_ALIGN_FILL);

		data.widthHint = Math.max(285, convertWidthInCharsToPixels(30));
		data.heightHint = Math.max(175, convertHeightInCharsToPixels(10));
		configsTable.setFont(parent.getFont());
		configsTable.setLayoutData(data);
		configsTable.setHeaderVisible(true);
		configsTable.setLinesVisible(true);

		Listener tableListener = new InternalTableListener(configsTable);
		configsTable.addListener(SWT.Dispose, tableListener);
		configsTable.addListener(SWT.KeyDown, tableListener);
		configsTable.addListener(SWT.MouseMove, tableListener);
		configsTable.addListener(SWT.MouseHover, tableListener);
		configsTable.addListener(SWT.Selection, tableListener);
	}

	private void createOperationControl(Composite parent) {
		Composite controlColumn = new Composite(parent, SWT.NONE);
		GridData data = new GridData(GridData.FILL_VERTICAL);
		controlColumn.setLayoutData(data);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		controlColumn.setLayout(layout);

		Label placeholder = new Label(controlColumn, SWT.NONE);
		GridData placeholderData = new GridData(SWT.TOP);
		placeholderData.heightHint = convertVerticalDLUsToPixels(12);
		placeholder.setLayoutData(placeholderData);

		selectAllButton = createButton(controlColumn, "Select All");
		deselectAllButton = createButton(controlColumn, "Deselect All");
		ignoreButton = createButton(controlColumn, "Ignore");
		showAllButton = createButton(controlColumn, "Show All");

		MouseListener buttonLister = new InternalButtonLister();
		selectAllButton.addMouseListener(buttonLister);
		deselectAllButton.addMouseListener(buttonLister);
		ignoreButton.addMouseListener(buttonLister);
		showAllButton.addMouseListener(buttonLister);
	}

	/**
	 * 刷新按钮状态
	 */
	private void updateButtons() {
		List<IConfigItem> alternative = getSelectedAlternativeConfigItems();
		ignoreButton.setEnabled(alternative.size() > 0);
		deselectAllButton.setEnabled(alternative.size() > 0);
		selectAllButton.setEnabled(getSelectedConfigItems().size() != configsTable.getItemCount());
	}

	protected void okPressed() {
		try {
			doUpdate();
		}
		catch (Throwable e) {
			Status status = new Status(IStatus.ERROR, Constants.PLUGIN_ID, 1, e.getMessage(), e);
			ErrorDialog.openError(this.getShell(), ErrorDialog.DEFAULT_TITLE, e.getMessage(), status);
			throw new RuntimeException(e);
		}
		super.okPressed();
	}

	public int open() {
		try {
			refreshView(false);
			updateButtons();
		}
		catch (Throwable e) {
			Status status = new Status(IStatus.ERROR, Constants.PLUGIN_ID, 1, e.getMessage(), e);
			ErrorDialog.openError(this.getShell(), ErrorDialog.DEFAULT_TITLE, e.getMessage(), status);
			throw new RuntimeException(e);
		}
		return super.open();
	}

	/**
	 * 刷新界面
	 * @param showIgnored 是否显示已经忽略的更新
	 * @throws Throwable
	 */
	private void refreshView(final boolean showIgnored) throws Throwable {
		//				final IConfigItems items = new ConfigItemsLoader().load(IDEUtil.getConfigFolder());
		final IConfigItems items = new ConfigItemsLoader().load(null);
		//		ExceptionHandler.throwException(((ConfigItems) items).doSync());
		//		if (canUpdate(items)) {
		//			/*
		//			 * 原文：可更新的配置信息
		//			 */
		//			this.setTitle("\u53ef\u66f4\u65b0\u7684\u914d\u7f6e\u4fe1\u606f");
		//			this.getButton(OK).setText("Update");
		//		}
		//		else {
		//			/*
		//			 * 原文：太棒啦，您的开发环境的配置已经是最新的
		//			 */
		//			this.setTitle("\u592a\u68d2\u5566\uff0c\u60a8\u7684\u5f00\u53d1\u73af\u5883\u7684\u914d\u7f6e\u5df2\u7ecf\u662f\u6700\u65b0\u7684\uff01");
		//			this.getButton(OK).setText("OK");
		//		}
		configsTable.removeAll();
		configsTable.setData(items);
		for (IConfigItem configItem : items.getConfigItems()) {
			//			if (!configItem.canUpdate() || !showIgnored && isIgnored(configItem)) {
			//				continue;
			//			}
			TableItem item = new TableItem(configsTable, SWT.NONE);
			item.setText(configItem.getDescription());
			item.setData(configItem);
			//			if (configItem.isForceToUpdate()) {
			item.setChecked(true);
			item.setGrayed(true);
			//			}
		}
	}

	/**
	 * 刷新界面，假如抛出异常，则截获异常并显示异常信息，并不重新抛出
	 */
	private void refreshViewShowError(final boolean showIgnored) {
		try {
			refreshView(showIgnored);
		}
		catch (Throwable e) {
			Status status = new Status(IStatus.ERROR, Constants.PLUGIN_ID, 1, e.getMessage(), e);
			ErrorDialog.openError(this.getShell(), ErrorDialog.DEFAULT_TITLE, e.getMessage(), status);
		}
	}

	/**
	 * 执行更新
	 * @throws Throwable
	 */
	private void doUpdate() throws Throwable {
		MultiStatus status = new MultiStatus(Constants.PLUGIN_ID, IStatus.OK, "", null);
		List<IConfigItem> list = getSelectedConfigItems();
		for (Iterator<IConfigItem> iter = list.iterator(); iter.hasNext();) {
			IConfigItem item = iter.next();
			if (item.canUpdate()) {
				status.add(item.update());
			}
		}
		if (!status.isOK()) {
			ExceptionHandler.throwException(status);
		}
	}

	/**
	 * 忽略选中的配置
	 */
	private void doIgnore() {
		//				List<IConfigItem> list = getSelectedAlternativeConfigItems();
		//				for (Iterator<IConfigItem> iter = list.iterator(); iter.hasNext();) {
		//					IgnoredConfigStore.getInstance().reg(iter.next().getId());
		//				}
		//				refreshViewShowError(false);
	}

	//	private boolean canUpdate(IConfigItems items) {
	//		for (IConfigItem item : items.getConfigItems()) {
	//			if (item.canUpdate() && !isIgnored(item)) {
	//				return true;
	//			}
	//		}
	//		return false;
	//	}

	/**
	 * 是否是忽略更新的配置
	 * @param item
	 * @return
	 */
	//	private boolean isIgnored(IConfigItem item) {
	//		return !item.isForceToUpdate() && IgnoredConfigStore.getInstance().contains(item.getId());
	//	}

	private List<IConfigItem> getSelectedConfigItems() {
		List<IConfigItem> list = new ArrayList<IConfigItem>();
		for (TableItem item : configsTable.getItems()) {
			if (item.getChecked()) {
				list.add((IConfigItem) item.getData());
			}
		}
		return list;
	}

	//
	private List<IConfigItem> getSelectedAlternativeConfigItems() {
		List<IConfigItem> all = getSelectedConfigItems();
		List<IConfigItem> alternative = new ArrayList<IConfigItem>();
		for (Iterator<IConfigItem> iter = all.iterator(); iter.hasNext();) {
			IConfigItem item = iter.next();
			if (!item.isForceToUpdate()) {
				alternative.add(item);
			}
		}
		return alternative;
	}

	private class InternalButtonLister implements MouseListener {

		public void mouseDoubleClick(MouseEvent event) {

		}

		public void mouseDown(MouseEvent event) {

		}

		public void mouseUp(MouseEvent event) {
			if (event.widget == selectAllButton) {
				for (TableItem item : configsTable.getItems()) {
					item.setChecked(true);
				}
			}
			else if (event.widget == deselectAllButton) {
				for (TableItem item : configsTable.getItems()) {
					if (!((IConfigItem) item.getData()).isForceToUpdate()) {
						item.setChecked(false);
					}
				}
			}
			else if (event.widget == ignoreButton) {
				doIgnore();
			}
			else if (event.widget == showAllButton) {
				//				IgnoredConfigStore.getInstance().clear();
				refreshViewShowError(true);
			}
			updateButtons();
		}

	}

	private class InternalTableListener implements Listener {

		private Table table;

		private Shell shell;

		private Display display;

		private Shell tip = null;

		private Label label = null;

		final Listener labelListener;

		public InternalTableListener(Table table) {
			this.table = table;
			shell = table.getShell();
			display = shell.getDisplay();
			labelListener = new Listener() {
				public void handleEvent(Event event) {
					Label label = (Label) event.widget;
					Shell shell = label.getShell();
					switch (event.type) {
						case SWT.MouseExit:
							shell.dispose();
							break;
					}
				}
			};
		}

		public void handleEvent(Event event) {
			switch (event.type) {
				case SWT.Dispose:
				case SWT.KeyDown:
				case SWT.MouseMove: {
					if (tip == null)
						break;
					tip.dispose();
					tip = null;
					label = null;
					break;
				}
				case SWT.Selection:
					handleSelection(event);
					break;
				case SWT.MouseHover: {
					handleMouseHover(event);
					break;
				}
			}
		}

		private void handleSelection(Event event) {
			//			TableItem item = getTableItem(table, event);
			//			if (item == null || item.getChecked()) {
			//				updateButtons();
			//				return;
			//			}
			//			IConfigItem configItem = (IConfigItem) item.getData();
			//			if (configItem.isForceToUpdate()) {
			//				item.setChecked(true);
			//				item.setGrayed(true);
			//			}
			//			updateButtons();
		}

		private void handleMouseHover(Event event) {
			//			TableItem item = getTableItem(table, event);
			//			if (item == null) {
			//				return;
			//			}
			//			if (tip != null && !tip.isDisposed()) {
			//				tip.dispose();
			//			}
			//			tip = new Shell(shell, SWT.ON_TOP | SWT.TOOL);
			//			tip.setLayout(new FillLayout());
			//			label = new Label(tip, SWT.NONE);
			//			label.setForeground(display.getSystemColor(SWT.COLOR_INFO_FOREGROUND));
			//			label.setBackground(display.getSystemColor(SWT.COLOR_INFO_BACKGROUND));
			//			label.setData("_TABLEITEM", item);
			//			label.setText(getConfigItemTooltip((IConfigItem) item.getData()));
			//			label.addListener(SWT.MouseExit, labelListener);
			//			label.addListener(SWT.MouseDown, labelListener);
			//			Point size = tip.computeSize(SWT.DEFAULT, SWT.DEFAULT);
			//			Point pt = table.toDisplay(event.x + 10, event.y);
			//			tip.setBounds(pt.x, pt.y, size.x, size.y);
			//			tip.setVisible(true);
		}

		//		@SuppressWarnings("rawtypes")
		//		private String getConfigItemTooltip(IConfigItem item) {
		//			return ((ConfigItem) item).getSyncInfo();
		//		}
		//
		//		private TableItem getTableItem(Table table, Event event) {
		//			if (event.item != null && event.item instanceof TableItem) {
		//				return (TableItem) event.item;
		//			}
		//			TableItem item = table.getItem(new Point(event.x, event.y));
		//			if (item == null) {
		//				item = table.getItem(new Point(event.x + 20, event.y));
		//			}
		//			return item;
		//		}

	}
}
