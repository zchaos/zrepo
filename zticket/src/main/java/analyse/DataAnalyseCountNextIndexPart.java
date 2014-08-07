package analyse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import result.AnalyseResult;
import analyse.state.AnalyseState;

/**
 * 计算数据下一次出现出现的位置
 * 
 * 如对已有的[1,2,3,1,1,2,1,5,5,5,5,5,5],如果按照
 * 
 * 如对已有的[1,2,3,1,1,2,1,5,5,5,5,5,5]，出现概率排序为{{5,6},{1,4},{2,2},{3,1}}。
 * 如果下一个出现的是5，则概率排名为1;为2，则概率排名为3;为8,则概率排名为5
 */
public class DataAnalyseCountNextIndexPart {
	private int limit = -1;//只测试最近出现的次数

	private int initcount = -1;//在开始计算之前，initcount作为初始化数据的状态，不计入结果内

	private double[] datas;

	public DataAnalyseCountNextIndexPart(double[] datas) {
		this(datas, -1, -1);
	}

	public DataAnalyseCountNextIndexPart(double[] datas, int initcount, int limit) {
		this.datas = datas;
		this.initcount = initcount;
		this.limit = limit;
	}

	/**
	 * 开始分析
	 */
	public AnalyseResult analyse() {
		HashMap<Double, AnalyseState> count = new HashMap<Double, AnalyseState>();
		int len = datas == null ? 0 : datas.length;

		int start;
		int end = len;
		int initEnd;
		int initStart;
		if (limit > 0) {
			start = Math.max(0, end - limit);

			initEnd = Math.max(0, start - 1);
			if (initcount > 0) {
				initStart = Math.max(0, initEnd - initcount);
			}
			else {
				initStart = 0;
			}
		}
		else {
			if (initcount > 0) {
				initStart = 0;
				initEnd = initcount;

				start = initEnd + 1;
			}
			else {
				initStart = 0;
				initEnd = 0;
				start = 0;
			}
		}
		//初始化
		for (int i = initStart; i < initEnd; i++) {
			double data = datas[i];
			AnalyseState state = count.get(data);
			if (state == null) {
				state = new AnalyseState(data);
				count.put(data, state);
			}
			state.increase(i);
		}
		//开始计算
		HashMap<Integer, AnalyseState> result = new HashMap<Integer, AnalyseState>();
		for (int i = start; i < end; i++) {
			double data = datas[i];
			{//计算当前数据的index
				int index = getNumberIndex(count, data);

				AnalyseState state = result.get(index);
				if (state == null) {
					state = new AnalyseState(index);
					result.put(index, state);
				}
				state.increase(i);
			}
			{//更新数据记录
				AnalyseState state = count.get(data);
				if (state == null) {
					state = new AnalyseState(data);
					count.put(data, state);
				}
				state.increase(i);
			}
		}

		ArrayList<AnalyseState> list = new ArrayList<AnalyseState>(result.values());
		return new AnalyseResult(list);
	}

	/**
	 * 返回data在count中按概率排名，如果不在count中，则概率排名为count的长度
	 * @param count
	 * @param data
	 * @return
	 */
	private int getNumberIndex(HashMap<Double, AnalyseState> count, double data) {
		int size = count.size();
		AnalyseState r = count.get(data);
		if (r == null) {
			return size + 1;
		}
		AnalyseState[] ncs = (AnalyseState[]) count.values().toArray(new AnalyseState[size]);
		Arrays.sort(ncs, AnalyseState.COMPARATOR);
		for (int i = 0; i < size; i++) {
			AnalyseState state = ncs[i];
			if (state.getData() == data) {
				return i;
			}
		}
		return size + 1;
	}
}