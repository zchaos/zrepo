package analyse;

import java.util.Comparator;

public class DataAnalyseResultComparator implements
		Comparator<DataAnalyseResult> {

	public static final DataAnalyseResultComparator COMPARATOR = new DataAnalyseResultComparator();

	public int compare(DataAnalyseResult o1, DataAnalyseResult o2) {
		double m = o1.getValue() - o2.getValue();
		return m == 0 ? 0 : m > 0 ? -1 : 1;// 降序
	}

}
