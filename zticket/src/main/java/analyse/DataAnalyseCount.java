package analyse;

import java.util.ArrayList;
import java.util.HashMap;

import result.AnalyseResult;
import analyse.state.AnalyseState;

/**
 * 计算数据出现的次数
 */
public class DataAnalyseCount {
	private int limit = -1;//只测试最近出现的次数

	private double[] datas;

	public DataAnalyseCount(double[] datas) {
		this(datas, -1);
	}

	public DataAnalyseCount(double[] datas, int limit) {
		this.datas = datas;
		this.limit = limit;
	}

	/**
	 * 开始分析
	 */
	public AnalyseResult analyse() {
		HashMap<Double, AnalyseState> result = new HashMap<Double, AnalyseState>();
		int len = datas == null ? 0 : datas.length;
		int start = limit > 0 ? len - limit : 0;
		for (int i = start; i < len; i++) {
			double data = datas[i];
			AnalyseState state = result.get(data);
			if (state == null) {
				state = new AnalyseState(data);
				result.put(data, state);
			}
			state.increase(i);
		}

		ArrayList<AnalyseState> list = new ArrayList<AnalyseState>(result.values());
		return new AnalyseResult(list);
	}

}