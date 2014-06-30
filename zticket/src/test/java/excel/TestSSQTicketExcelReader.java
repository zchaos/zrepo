package excel;

import java.io.IOException;
import java.util.List;

import data.DataReader;
import data.ssq.SSQDataReader;

public class TestSSQTicketExcelReader {
	public static void main(String[] args) throws IOException {
		List<List<Integer>> datalist = SSQDataReader.getData();
		DataReader.printData(datalist);
	}
}
