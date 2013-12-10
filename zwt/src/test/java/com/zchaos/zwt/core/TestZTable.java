package com.zchaos.zwt.core;

public class TestZTable {
	public static void main(String[] args) {
		ZFrame jf = new ZFrame("简单表格");
		//定义二维数组作为表格数据  
		Object[][] tableData = { new Object[] { "李清照", 29, "女" }, new Object[] { "苏格拉底", 56, "男" },
				new Object[] { "李白", 35, "男" }, new Object[] { "弄玉", 18, "女" }, new Object[] { "虎头", 2, "男" } };

		//定义一维数据作为列标题  
		Object[] columnTitle = { "姓名", "年龄", "性别" };
		//以二维数组和一维数组来创建一个JTable对象  
		ZTable table = new ZTable(tableData, columnTitle);
		//将JTable对象放在JScrollPane中，并将该JScrollPane放在窗口中显示出来  
		jf.add(new ZScrollPane(table));
		jf.show();
	}
}
