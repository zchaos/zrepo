package analyse;

public class DataAnalyseResult {
	private double key;
	private double value;
	private double nextValue;

	public DataAnalyseResult(double key, double value) {
		this(key, value, -1);
	}

	public DataAnalyseResult(double key, double value, double nextValue) {
		this.key = key;
		this.value = value;
		this.nextValue = nextValue;
	}

	public double getKey() {
		return key;
	}

	public double getValue() {
		return value;
	}

	public double getNextValue() {
		return nextValue;
	}
}
