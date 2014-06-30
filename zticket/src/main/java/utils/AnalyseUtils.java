package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import analyse.DataAnalyseResult;
import analyse.DataAnalyseResultComparator;

public class AnalyseUtils {
	/**
	 * 根据datas的value降序排列
	 * 
	 * @param datas
	 * @return
	 */
	public static DataAnalyseResult[] trans2Result(Map<Double, Double> datas) {
		DataAnalyseResult[] rs = new DataAnalyseResult[datas.size()];
		int index = 0;
		Iterator<Double> it = datas.keySet().iterator();
		while (it.hasNext()) {
			Double key = it.next();
			rs[index++] = new DataAnalyseResult(key, datas.get(key));
		}
		Arrays.sort(rs, DataAnalyseResultComparator.COMPARATOR);
		return rs;
	}

	public static List<DataAnalyseResult> trans2listResult(
			Map<Double, Double> datas) {
		ArrayList<DataAnalyseResult> list = new ArrayList<DataAnalyseResult>(
				datas.size());
		Iterator<Double> it = datas.keySet().iterator();
		while (it.hasNext()) {
			Double key = it.next();
			list.add(new DataAnalyseResult(key, datas.get(key)));
		}
		Collections.sort(list, DataAnalyseResultComparator.COMPARATOR);
		return list;
	}

	public static void print(List<DataAnalyseResult> list) {
		int size = list.size();
		for (int i = 0; i < size; i++) {
			DataAnalyseResult result = list.get(i);
			double nextValue = result.getNextValue();
			if (nextValue < 0) {
				System.out.println((i + 1) + " " + result.getKey() + " "
						+ result.getValue());
			} else {
				System.out.println((i + 1) + " " + result.getKey() + " "
						+ result.getValue() + " " + result.getNextValue());
			}
		}
	}

	/**
	 * 将数据的次数转换为概率
	 * 
	 * @param datas
	 * @return
	 */
	public static Map<Double, Double> trans2Probability(
			Map<Double, Double> datas) {
		double count = 0;
		Iterator<Double> vit = datas.values().iterator();
		while (vit.hasNext()) {
			Double c = vit.next();
			count += c == null ? 0 : c.doubleValue();
		}

		HashMap<Double, Double> rs = new HashMap<Double, Double>(datas.size());
		Iterator<Double> kit = datas.keySet().iterator();
		while (kit.hasNext()) {
			Double key = kit.next();
			Double c = datas.get(key);
			rs.put(key, c * 1.0 / count);
		}
		return rs;
	}

	public static double[] trans2primary(List<Double> datas) {
		int count = datas.size();
		double[] rs = new double[count];
		for (int i = 0; i < count; i++) {
			rs[i] = datas.get(i).doubleValue();
		}
		return rs;
	}
}
