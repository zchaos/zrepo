package test.analyse;

import java.util.List;

import result.AnalyseResult;
import utils.AnalyseUtils;
import analyse.DataAnalyseCount;
import data.ssq.SSQDataReader;

public class TesDataAnalyseCount {
	public static void main(String[] args) {
		List<Integer> datalist = SSQDataReader.getBlueDataList();
		double[] datas = AnalyseUtils.trans2double(datalist);
		DataAnalyseCount analyse = new DataAnalyseCount(datas);
		AnalyseResult result = analyse.analyse();
		result.printResult();
	}
}
