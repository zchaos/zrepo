package com.zchaos.zface.internal.impl;

import java.util.List;

import com.zchaos.zface.api.ZITable;
import com.zchaos.zface.util.ZFaceFactory;

public class ZTableImpl implements ZITable {

	private List<ZITable> list = null;

	public ZTableImpl() {
		list = ZFaceFactory.createComponents(ZITable.class);
	}
}
