package excel;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	public static Workbook createWorkBook(String path) throws IOException {
		if (org.apache.commons.lang.StringUtils.isBlank(path)) {
			throw new IllegalArgumentException("参数错误!!!");
		}
		if (StringUtils.endsWithIgnoreCase(path, ".xls")) {
			return new HSSFWorkbook(new FileInputStream(path));
		} else if (StringUtils.endsWithIgnoreCase(path, ".xlsx")) {
			return new XSSFWorkbook(new FileInputStream(path));
		} else {
			throw new IllegalArgumentException("不支持除：xls/xlsx以外的文件格式!!!");
		}
	}
}
