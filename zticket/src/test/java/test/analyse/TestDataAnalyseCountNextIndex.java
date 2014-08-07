package test.analyse;

import java.util.List;

import result.AnalyseResult;
import utils.AnalyseUtils;
import analyse.DataAnalyseCountNextIndex;
import data.ssq.SSQDataReader;

public class TestDataAnalyseCountNextIndex {
	public static void main(String[] args) {
		List<Integer> datalist = SSQDataReader.getBlueDataList();
		double[] datas = AnalyseUtils.trans2double(datalist);
		DataAnalyseCountNextIndex analyse = new DataAnalyseCountNextIndex(datas);
		AnalyseResult result = analyse.analyse();
		result.printResult();
	}
}
