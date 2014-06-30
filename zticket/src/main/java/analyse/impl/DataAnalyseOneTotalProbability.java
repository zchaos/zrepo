package analyse.impl;

import java.util.List;
import java.util.Map;

import utils.AlgorithmUtils;
import utils.AnalyseUtils;
import analyse.DataAnalyse;
import analyse.DataAnalyseResult;

/**
 * 判断各项数据在数据中出现的概率
 * 
 * @author zhuchx
 */
public class DataAnalyseOneTotalProbability implements DataAnalyse {

	private int limit = -1;

	public DataAnalyseOneTotalProbability() {
	}

	public DataAnalyseOneTotalProbability(int limit) {
		this.limit = limit;
	}

	@Override
	public List<DataAnalyseResult> anayse(double[] datas) {
		Map<Double, Double> result = AlgorithmUtils.probabilityTotal(datas,
				limit);
		return AnalyseUtils.trans2listResult(result);
	}

}
