package com.zchaos.ztest.example.swt.table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class TableDemo {
	private TableEditor editor = null;

	private Table table = null;

	public static void main(String[] args) {
		new TableDemo();
	}

	public TableDemo() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setText("table小工程");
		createTable(shell);
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	/**
	 * 创建表格
	 * 
	 * @param shell
	 */
	private void createTable(final Shell shell) {
		table = new Table(shell, SWT.MULTI | SWT.FULL_SELECTION);
		editor = new TableEditor(table);
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		TableColumn col1 = new TableColumn(table, SWT.LEFT);
		col1.setText("姓名");
		col1.setWidth(100);
		TableColumn col2 = new TableColumn(table, SWT.LEFT);
		col2.setText("年龄");
		col2.setWidth(100);
		TableColumn col5 = new TableColumn(table, SWT.LEFT);
		col5.setText("性别");
		col5.setWidth(100);
		TableColumn col3 = new TableColumn(table, SWT.LEFT);
		col3.setText("地址");
		col3.setWidth(100);
		TableColumn col4 = new TableColumn(table, SWT.LEFT);
		col4.setText("电话");
		col4.setWidth(100);
		/**
		 * 添加表格数据
		 */
		new TableItem(table, SWT.LEFT).setText(new String[] { "张三", "22", "男", "重庆市", "13022332356" });
		new TableItem(table, SWT.LEFT).setText(new String[] { "小桥", "22", "女", "上海市", "13029872222" });
		new TableItem(table, SWT.LEFT).setText(new String[] { "小可", "22", "男", "北京市", "13006800123" });
		new TableItem(table, SWT.LEFT).setText(new String[] { "王五", "31", "男", "武汉市", "13098982201" });
		new TableItem(table, SWT.LEFT).setText(new String[] { "天启", "29", "男", "南宁市", "13024374001" });
		new TableItem(table, SWT.LEFT).setText(new String[] { "高乐", "25", "女", "天津市", "13088888888" });
		// 删除菜单
		Menu menu1 = new Menu(shell, SWT.POP_UP);
		table.setMenu(menu1);
		MenuItem menuitem1 = new MenuItem(menu1, SWT.PUSH);
		menuitem1.setText("删除");
		menuitem1.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				MessageBox mbox = new MessageBox(shell, SWT.DIALOG_TRIM | SWT.ICON_INFORMATION);
				mbox.setText("删除成功");
				mbox.setMessage("删除了" + table.getSelectionCount() + "条记录");
				table.remove(table.getSelectionIndices());
				mbox.open();
			}
		});
		// 修改table
		{
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDoubleClick(MouseEvent e) {
					Control c = editor.getEditor();
					if (c != null) {
						c.dispose();
					}
					// 得到选中的行
					Point point = new Point(e.x, e.y);
					// MessageDialog.openInformation(shell, null,
					// point.x+","+point.y);
					final TableItem tableitem = table.getItem(point);
					// 得到选中的列
					int column = -1;
					for (int i = 0; i < table.getColumnCount(); i++) {
						Rectangle rec = tableitem.getBounds(i);
						if (rec.contains(point))
							column = i;
					}
					final int col1 = column;

					// 修改年龄，进行微调
					if (col1 == 1) {
						final Spinner spiner = new Spinner(table, SWT.NONE);
						spiner.setMaximum(120);
						spiner.setMinimum(1);
						spiner.setSelection(new Integer(tableitem.getText(1)));
						editor.setEditor(spiner, tableitem, col1);
						spiner.addModifyListener(new ModifyListener() {

							@Override
							public void modifyText(ModifyEvent e) {
								tableitem.setText(col1, String.valueOf(spiner.getSelection()));
							}
						});
					}
					else if (col1 == 2) {
						// 修改性别用下拉列表
						final Combo comb = new Combo(table, SWT.READ_ONLY);
						comb.setItems(new String[] { "男", "女", "未知" });
						comb.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent e) {
								tableitem.setText(col1, comb.getText());
								comb.dispose();
								super.widgetSelected(e);
							}
						});
						editor.setEditor(comb, tableitem, column);
					}
					else {
						// 其他的修改都是用文本框
						final Text txt = new Text(table, SWT.NONE);
						txt.setText(tableitem.getText(col1));
						txt.forceFocus();
						editor.setEditor(txt, tableitem, col1);
						txt.addModifyListener(new ModifyListener() {

							@Override
							public void modifyText(ModifyEvent e) {
								tableitem.setText(col1, txt.getText());

							}
						});
					}
				}
			});
		}
	}
}