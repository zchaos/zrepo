package com.zchaos.zface.util;

import java.io.File;
import java.io.IOException;

import com.zchaos.zutil.FileUtils;

public abstract class ZFaceClassLoader extends ClassLoader {
	private static String PROJECT = "zface";

	private static String CLASSES_PATH_TEST = "test-classes";

	private static String CLASSES_PATH = "classes";

	protected abstract String getProject();

	protected abstract String getClassZFactory();

	public Class<?> findClass(String name) {
		String classPath = getClassPath(name);
		byte[] data = getClassData(classPath);
		Class<?> clazz = defineClass(name, data, 0, data.length);
		return clazz;
	}

	public Class<?> findFactoryClass() {
		return findClass(getClassZFactory());
	}

	/**
	 * 返回class文件的内容
	 * @param classPath
	 * @return
	 */
	private byte[] getClassData(String classPath) {
		File file = new File(classPath);
		try {
			return FileUtils.readFileToByteArray(file);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获得类的路径
	 * @param name
	 * @return
	 */
	private String getClassPath(String name) {
		String path = getProjectBaseDir();
		String classPath = name.replaceAll("\\.", "/");
		return path + classPath + ".class";
	}

	/**
	 * 获得子类对应的工程路径
	 * @return
	 */
	private String getProjectBaseDir() {
		String basedir = getCurrentProjectBaseDir();
		String project = getProject();
		int index = basedir.lastIndexOf(PROJECT);
		if (index == -1) {
			throw new RuntimeException("当前工程路径获得的不正确，没有包含工程名");
		}
		return basedir.substring(0, index) + project + basedir.substring(index + PROJECT.length());
	}

	/**
	 * 获得当前工程的class的根路径
	 * @return
	 */
	private String getCurrentProjectBaseDir() {
		String path = this.getResource(".").getPath();
		int index = path.lastIndexOf(CLASSES_PATH_TEST);
		if (index == -1) {
			return path;
		}
		return path.substring(0, index) + CLASSES_PATH + path.substring(index + CLASSES_PATH_TEST.length());
	}

	public static void main(String[] args) {
		String path = ZFaceClassLoader.class.getResource(".").getPath();
		System.out.println(path);
	}
}