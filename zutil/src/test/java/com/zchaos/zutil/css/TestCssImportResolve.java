package com.zchaos.zutil.css;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

public class TestCssImportResolve {
	public static void main(String[] args) throws HttpException, IOException {
		String url = "http://localhost:4080/ciexam/static-files-aggcss/sz.bi.api.aggregation/skin/default/sz-bi-api-all-import.css";
		String rs = CSSImportResolve.importResolove2Str(url);
		System.out.println(rs);
	}
}
