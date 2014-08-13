package test;

import blanco.commons.sql.format.BlancoSqlFormatter;
import blanco.commons.sql.format.BlancoSqlFormatterException;
import blanco.commons.sql.format.BlancoSqlRule;

public class TestSqlFormat {
	public static void main(String[] args) throws BlancoSqlFormatterException {
		String sql = "SELECT name,value FROM a WHERE name IN ('db block gets','consistent gets', 'physical reads')";
		BlancoSqlFormatter sqlformatter = new BlancoSqlFormatter(new BlancoSqlRule());
		System.out.println(sqlformatter.format(sql));
	}
}
