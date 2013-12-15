package com.zchaos.zface.util.swing;

public class TestZSWINGClassLoader {
	public static void main(String[] args) throws ClassNotFoundException {
		ZSWINGClassLoader loader = new ZSWINGClassLoader();
		Class<?> clazz = loader.findFactoryClass();
		System.out.println(clazz);
	}
}
