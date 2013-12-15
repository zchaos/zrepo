package com.zchaos.zface.internal;

import java.util.List;

import com.zchaos.zface.ZFactory;
import com.zchaos.zface.api.ZITable;

public class ZTableImpl implements ZITable {

	private List<ZITable> list = null;

	public ZTableImpl() {
		list = ZFactory.createComponents(ZITable.class);
	}
}
