package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 算法工具
 * 
 * @author zhuchx
 * 
 */
public class AlgorithmUtils {
	/**
	 * 计算data中的数据在data中出现的次数
	 * 
	 * @param data
	 * @return Map，key为data中出现的数据，value为data中出现的次数
	 */
	public static Map<Double, Double> probabilityTotal(double[] datas) {
		return probabilityTotal(datas, -1);
	}

	/**
	 * 与probabilityTotal作用相同，但是只计算最后的limit个数据
	 * 
	 * @param datas
	 * @param limit
	 *            最后的多少个数据，如100表示只计算datas最后100个数据。小于等于0表示计算全部
	 * @return
	 */
	public static Map<Double, Double> probabilityTotal(double[] datas, int limit) {
		HashMap<Double, Double> result = new HashMap<Double, Double>();
		int len = datas == null ? 0 : datas.length;
		int start = limit > 0 ? len - limit : 0;
		for (int i = start; i < len; i++) {
			double data = datas[i];
			Double count = result.get(data);
			if (count == null) {
				count = Double.valueOf(1);
			} else {
				count = Double.valueOf(count.doubleValue() + 1);
			}
			result.put(data, count);
		}
		return result;
	}
}
