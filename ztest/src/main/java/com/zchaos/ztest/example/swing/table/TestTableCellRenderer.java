package com.zchaos.ztest.example.swing.table;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * 使用单元格绘制器
 * 单元格通常都是字符串，我们传入的是DefaultTableModel它最终调用toString方法来绘制表格
 * 此类说明自定义单元格的使用。这里只是实现了每列不同的控件，还可以自定义一个表格每行每列都是不同的控件，那就要得到单元格再setCellRender。
 * @author zhuchx
 *
 */
public class TestTableCellRenderer {
	JFrame jf = new JFrame("使用单元格绘制器");

	JTable table;

	//定义二维数组作为表格数据  
	Object[][] tableData = { new Object[] { "李清照", 29, "女", new ImageIcon("icon/3.gif"), true },
			new Object[] { "苏格拉底", 56, "男", new ImageIcon("icon/1.gif"), false },
			new Object[] { "李白", 35, "男", new ImageIcon("icon/4.gif"), true },
			new Object[] { "弄玉", 18, "女", new ImageIcon("icon/2.gif"), true },
			new Object[] { "虎头", 2, "男", new ImageIcon("icon/5.gif"), true } };

	//定义一维数据作为列标题  
	String[] columnTitle = { "姓名", "年龄", "性别", "主头像", "是否中国人" };

	public void init() {
		//以二维数组和一维数组来创建一个ExtendedTableModel对象  
		ExtendedTableModel model = new ExtendedTableModel(columnTitle, tableData);
		//以ExtendedTableModel来创建JTable  
		table = new JTable(model);
		table.setRowSelectionAllowed(false);
		table.setRowHeight(40);
		//获取第三列  
		TableColumn lastColumn = table.getColumnModel().getColumn(2);
		//对第三列采用自定义的单元格绘制器  
		lastColumn.setCellRenderer(new GenderTableCellRenderer());

		//将JTable对象放在JScrollPane中，并将该JScrollPane放在窗口中显示出来  
		jf.add(new JScrollPane(table));
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}

	public static void main(String[] args) {
		new TestTableCellRenderer().init();
	}
}

class ExtendedTableModel extends DefaultTableModel {
	//重新提供一个构造器，该构造器的实现委托给DefaultTableModel父类  
	public ExtendedTableModel(String[] columnNames, Object[][] cells) {
		super(cells, columnNames);
	}

	//重写getColumnClass方法，根据每列的第一个值来返回其真实的数据类型  
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}

class GenderTableCellRenderer extends JPanel implements TableCellRenderer {
	private String cellValue;

	//定义图标的宽度和高度  
	final int ICON_WIDTH = 23;

	final int ICON_HEIGHT = 21;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		cellValue = (String) value;
		//设置选中状态下绘制边框  
		if (hasFocus) {
			setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
		}
		else {
			setBorder(null);
		}
		return this;
	}

	//重写paint方法，负责绘制该单元格内容  
	public void paint(Graphics g) {
		//如果表格值为"男"或"male"，则绘制一个男性图标  
		if (cellValue.equalsIgnoreCase("男") || cellValue.equalsIgnoreCase("male")) {
			drawImage(g, new ImageIcon("icon/male.gif").getImage());
		}
		//如果表格值为"女"或"female"，则绘制一个女性图标  
		if (cellValue.equalsIgnoreCase("女") || cellValue.equalsIgnoreCase("female")) {
			drawImage(g, new ImageIcon("icon/female.gif").getImage());
		}
	}

	//绘制图标的方法  
	private void drawImage(Graphics g, Image image) {
		g.drawImage(image, (getWidth() - ICON_WIDTH) / 2, (getHeight() - ICON_HEIGHT) / 2, null);
	}
}
