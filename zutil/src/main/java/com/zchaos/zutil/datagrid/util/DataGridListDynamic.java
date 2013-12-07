package com.zchaos.zutil.datagrid.util;

import java.util.ArrayList;
import java.util.List;

public class DataGridListDynamic<T> {
	private List<T> cells = new ArrayList<T>();

	private int count;

	public void add(T t) {
		cells.add(t);
	}

	public void set(int index, T t) {

	}

	public int size() {
		return 0;
	}

	private void ensureSize(int index) {
		int size = cells.size();
		if (index < size) {
			return;
		}

		int len = index + 1;
		for (int i = size; i < len; i++) {
			cells.set(0, null);
		}
	}
}
