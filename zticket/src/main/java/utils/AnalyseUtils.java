package utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AnalyseUtils {
	/**
	 * 将数据的次数转换为概率
	 * 
	 * @param datas
	 * @return
	 */
	public static Map<Double, Double> trans2Probability(Map<Double, Double> datas) {
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

	public static double[] trans2double(List<Integer> datas) {
		int count = datas.size();
		double[] rs = new double[count];
		for (int i = 0; i < count; i++) {
			rs[i] = datas.get(i).doubleValue();
		}
		return rs;
	}
}
