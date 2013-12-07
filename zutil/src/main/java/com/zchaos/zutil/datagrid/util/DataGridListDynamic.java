package com.zchaos.zutil.datagrid.util;

import java.util.ArrayList;
import java.util.List;

public class DataGridListDynamic<T> {
	private List<T> list = new ArrayList<T>(16);

	private int count;

	public T get(int index) {
		int size = list.size();
		if (index >= size) {
			return null;
		}
		return list.get(index);
	}

	public void add(T t) {
		list.add(t);
		count++;
	}

	public void set(int index, T t) {
		ensureSize(index);
		list.set(index, t);
		count = Math.max(index + 1, count);
	}

	public int size() {
		return count;
	}

	private void ensureSize(int index) {
		int size = list.size();
		if (index < size) {
			return;
		}

		int len = index + 1;//最少要这么长
		len = (int) Math.ceil(len + len / 2);//多分配部分空间，避免每次都调整

		for (int i = size; i < len; i++) {
			list.add(null);
		}
	}
}
