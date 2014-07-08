package test.analyse;

import java.util.List;

import utils.AnalyseUtils;
import data.ssq.SSQDataReader;
import analyse.DataAnalyseResult;
import analyse.impl.DataAnalyseOneTotalProbability;

public class TesDataAnalyseOneTotalProbability {
	public static void main(String[] args) {
		List<Integer> datalist = SSQDataReader.getBlueDataList();
		double[] datas = AnalyseUtils.trans2double(datalist);
		DataAnalyseOneTotalProbability analyse = new DataAnalyseOneTotalProbability();
		List<DataAnalyseResult> list = analyse.anayse(datas);
		AnalyseUtils.print(list);
	}
}
