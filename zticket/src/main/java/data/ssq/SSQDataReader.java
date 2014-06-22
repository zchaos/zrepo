package data.ssq;

import java.util.ArrayList;
import java.util.List;

import data.DataReader;

public class SSQDataReader {
	private static final String FILE_SSQ = "/data/ssq/ssq.xlsx";
	private static List<List<Integer>> SSQDATA = DataReader.getData(FILE_SSQ,
			null);
	private static final int INDEX_BLUE = 8;
	private static final int INDEX_YEAR = 0;

	public static List<List<Integer>> getData() {
		return SSQDATA;
	}

	/**
	 * 获得蓝球
	 * 
	 * @param year
	 * @return
	 */
	public static List<Integer> getBlueDataList(int year) {
		List<Integer> blueData = new ArrayList<Integer>();

		List<List<Integer>> datalist = SSQDATA;
		int listsize = datalist.size();
		for (int i = 0; i < listsize; i++) {
			List<Integer> data = datalist.get(i);
			int blueValue = data.get(INDEX_BLUE);
			if (isYear(data, year)) {
				blueData.add(blueValue);
			}
		}
		return blueData;
	}

	/**
	 * 获得某一年的数据
	 * 
	 * @param year
	 * @return
	 */
	public static List<List<Integer>> getData(int year) {
		List<List<Integer>> yearData = new ArrayList<List<Integer>>();

		List<List<Integer>> datalist = SSQDATA;
		int listsize = datalist.size();
		for (int i = 0; i < listsize; i++) {
			List<Integer> data = datalist.get(i);
			if (isYear(data, year)) {
				yearData.add(data);
			}
		}
		return yearData;
	}

	private static boolean isYear(List<Integer> data, int year) {
		return year <= 0 || data.get(INDEX_YEAR).intValue() == year;
	}
}
