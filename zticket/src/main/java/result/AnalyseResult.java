package result;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.zchaos.zutil.StringUtils;

import analyse.state.AnalyseState;

public class AnalyseResult {
	public static final Comparator<AnalyseState> COMPARATOR = new Comparator<AnalyseState>() {
		public int compare(AnalyseState n1, AnalyseState n2) {
			if (n1.count != n2.count) {
				return n2.count - n1.count;
			}
			return n1.lastIndex - n2.lastIndex;
		}
	};

	private List<AnalyseState> datas;

	public AnalyseResult(List<AnalyseState> datas) {
		this.datas = datas;
	}

	public void printResult(Writer w) throws IOException {
		String str = toString();
		if (StringUtils.isNotEmpty(str)) {
			w.write(str);
		}
	}

	public void printResult(PrintStream ps) throws IOException {
		String str = toString();
		if (StringUtils.isNotEmpty(str)) {
			ps.print(str);
		}
	}

	public void printResult() {
		String str = toString();
		if (StringUtils.isNotEmpty(str)) {
			System.out.println(str);
		}
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		Collections.sort(datas, COMPARATOR);
		int size = datas.size();
		for (int i = 0; i < size; i++) {
			AnalyseState state = datas.get(i);
			builder.append(state.getData()).append(":").append(state.getCount()).append("\r\n");
		}
		return builder.toString();
	}
}
