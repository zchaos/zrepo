package utils;

import result.AnalyseResult;
import analyse.DataAnalyseCountNextIndex;

import com.zchaos.zutil.ArrayUtils;

public class TestAlgorithmUtils {
	public static void main(String[] args) {
		double[] base = { 1, 2, 3, 1, 1, 2, 1, 5, 5, 5, 5, 5, 5 };
		printResult(base, 5);
		printResult(base, 5, 5);
	}

	private static void printResult(double[] base, double... value) {
		System.out.println("==================");
		double[] data = ArrayUtils.addAll(base, value);
		DataAnalyseCountNextIndex analyse = new DataAnalyseCountNextIndex(data, base.length, value.length);
		AnalyseResult result = analyse.analyse();
		result.printResult();
	}
}
