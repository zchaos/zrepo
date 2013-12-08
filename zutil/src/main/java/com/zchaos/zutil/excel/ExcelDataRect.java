package com.zchaos.zutil.excel;


/**
 * excel文件的数据范围。包括起始行，起始列，总行数，总列数
 * @author zhuchx
 *
 */
public class ExcelDataRect {
	private int row;

	private int col;

	private int rowcount;

	private int colcount;

	public ExcelDataRect(int row, int col, int rowcount, int colcount) {
		this.row = row;
		this.col = col;
		this.rowcount = rowcount;
		this.colcount = colcount;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public int getRowCount() {
		return rowcount;
	}

	public int getColCount() {
		return colcount;
	}
}
