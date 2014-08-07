package analyse.state;

import java.util.Comparator;

public class AnalyseState {
	public int lastIndex = -1;//最近一次出现的位置

	public int count = 0;//出现的次数

	public double data;//当前数据

	public static final Comparator<AnalyseState> COMPARATOR = new Comparator<AnalyseState>() {
		public int compare(AnalyseState n1, AnalyseState n2) {
			if (n1.count != n2.count) {
				return n2.count - n1.count;
			}
			return n1.lastIndex - n2.lastIndex;
		}
	};

	public AnalyseState(double data) {
		this.data = data;
	}

	public void increase(int index) {
		count++;
		lastIndex = index;
	}

	public double getData() {
		return data;
	}

	public int getCount() {
		return count;
	}
}
