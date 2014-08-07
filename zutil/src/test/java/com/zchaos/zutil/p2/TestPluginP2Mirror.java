package com.zchaos.zutil.p2;

import java.io.File;

public class TestPluginP2Mirror {
	public static void main(String[] args) {
		String name = "aptana";
		String location = "http://download.aptana.com/studio3/plugin/install";
		File dest = new File("/ztmp/eclipse/plugins/" + name);
		String xml = PluginP2Mirror.createMirror(name, location, dest);
		System.out.println(xml);
	}
}
