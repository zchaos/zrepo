package test;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * 测试表格无表头
 */
public class TestTableNoHeader {
	public static void main(String[] args) {
		JFrame jf = new JFrame("简单表格");

		TableModel datas = new DefaultTableModel(2, 2);
		datas.setValueAt("李清照", 0, 0);
		datas.setValueAt("女", 0, 1);
		datas.setValueAt("苏格拉底", 1, 0);
		datas.setValueAt("男", 1, 1);
		JTable table = new JTable(datas);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();//CellRenderer 的预选高度为 0
		renderer.setPreferredSize(new Dimension(0, 0));
		table.getTableHeader().setDefaultRenderer(renderer);
		table.getTableHeader().setVisible(false);//加不加这行都不要紧
		//将JTable对象放在JScrollPane中，并将该JScrollPane放在窗口中显示出来  
		//		jf.add(new JScrollPane(table));

		JScrollPane tableScrollPane = new JScrollPane();
		tableScrollPane.setViewportView(table);//或者new JScrollPane(table)
		jf.add(tableScrollPane);

		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}

}
