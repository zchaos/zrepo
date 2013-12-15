package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
	public static <T> void main(String[] args) {
		Map<Class<T>, Class<T>> map = new HashMap<Class<T>, Class<T>>();
		List<T> list = new ArrayList<T>();
	}
}
