package data;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.zchaos.zutil.ExcelUtils;
import com.zchaos.zutil.NumberUtils;
import com.zchaos.zutil.StringUtils;
import com.zchaos.zutil.excel.ExcelDataRect;

public class DataReader {
	private static final int INDEX_YEAR = 0;// 年份
	private static final int INDEX_QS = 1;// 期数

	private static final Comparator<List<Integer>> COMPARATOR_DATALIST = new Comparator<List<Integer>>() {
		public int compare(List<Integer> o1, List<Integer> o2) {
			int y1 = o1.get(INDEX_YEAR);
			int y2 = o2.get(INDEX_YEAR);
			int q1 = o1.get(INDEX_QS);
			int q2 = o2.get(INDEX_QS);
			return y1 == y2 ? q1 - q2 : y1 - y2;
		}
	};

	public static void printData(List<List<Integer>> datalist)
			throws IOException {
		StringWriter w = new StringWriter();
		writeData(datalist, w);
		System.out.println(w.toString());
	}

	public static void writeData(List<List<Integer>> datalist, Writer w)
			throws IOException {
		int listsize = datalist.size();
		for (int i = 0; i < listsize; i++) {
			List<Integer> data = datalist.get(i);
			int size = data.size();
			for (int j = 0; j < size; j++) {
				if (j != 0) {
					w.write('\t');
				}
				Integer value = data.get(j);
				w.write(String.valueOf(value));
			}
			w.write("\r\n");
		}
	}

	public static List<List<Integer>> getData(String path, String sheetName) {
		try {
			Workbook workBook = ExcelUtils.createWorkBook(path,
					DataReader.class);
			ArrayList<List<Integer>> datalist = new ArrayList<List<Integer>>();
			int len = workBook.getNumberOfSheets();
			for (int i = 0; i < len; i++) {
				Sheet sheet = workBook.getSheetAt(i);

				if (StringUtils.isEmpty(sheetName)
						|| StringUtils.equalsIgnoreCase(sheetName,
								sheet.getSheetName())) {
					sheet2DataGrid(datalist, sheet);
				}
			}
			ArrayList<List<Integer>> fixdatalist = toFixed(datalist);
			Collections.sort(fixdatalist, COMPARATOR_DATALIST);
			return fixdatalist;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void sheet2DataGrid(List<List<Integer>> datalist, Sheet sheet)
			throws IOException {
		ExcelDataRect rect = ExcelUtils.getSheetRect(sheet);
		int startRow = rect.getRow();
		int endRow = startRow + rect.getRowCount();

		for (int i = startRow; i < endRow; i++) {
			Row sheetRow = sheet.getRow(i);
			if (!isData(sheetRow)) {
				continue;
			}
			ArrayList<Integer> data = new ArrayList<Integer>();
			short startCol = sheetRow.getFirstCellNum();
			short endCol = sheetRow.getLastCellNum();

			for (int j = startCol; j < endCol; j++) {
				Cell cell = sheetRow.getCell(j);
				String value = ExcelUtils.sheet2DataGrid(cell);
				int num = Double.valueOf(value).intValue();
				data.add(num);
			}

			datalist.add(data);
		}
	}

	/**
	 * 判断是否头部
	 * 
	 * @param dataGrid
	 * @param sheetRow
	 * @return
	 * @throws IOException
	 */
	private static boolean isData(Row sheetRow) throws IOException {
		short startCol = sheetRow.getFirstCellNum();
		Cell cell = sheetRow.getCell(startCol);
		String value = ExcelUtils.sheet2DataGrid(cell);
		return NumberUtils.isNumber(value);
	}

	public static ArrayList<List<Integer>> toFixed(List<List<Integer>> datalist) {
		ArrayList<List<Integer>> newDataList = new ArrayList<List<Integer>>();
		// 会将期号分为年份和期号两部分，所以列会加1
		int listsize = datalist.size();
		for (int i = 0; i < listsize; i++) {
			List<Integer> data = datalist.get(i);
			ArrayList<Integer> newData = new ArrayList<Integer>();
			// 第一个是期号，如13001，会分为13和001
			int qs = data.get(0);
			String value = String.valueOf(qs);
			String year = value.substring(0, value.length() - 3);
			String num = value.substring(year.length());

			newData.add(NumberUtils.toInt(year) + 2000);
			newData.add(NumberUtils.toInt(num));

			int size = data.size();
			for (int j = 1; j < size; j++) {
				newData.add(data.get(j));
			}
			newDataList.add(newData);
		}

		return newDataList;
	}
}
