package test.analyse;

import java.util.List;

import utils.AnalyseUtils;
import data.ssq.SSQDataReader;
import analyse.DataAnalyseResult;
import analyse.impl.DataAnalyseOneTotalProbability;

public class TesDataAnalyseOneTotalProbability {
	public static void main(String[] args) {
		double[] datas = SSQDataReader.getBlueDataArray();
		DataAnalyseOneTotalProbability analyse = new DataAnalyseOneTotalProbability(
				0);
		List<DataAnalyseResult> list = analyse.anayse(datas);
		AnalyseUtils.print(list);
	}
}
