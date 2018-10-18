package com.share.lifetime.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.util.ResourceUtils;

public class CSVUtilsTest {

	@Test
	public void testParseExcelCSVFile() {
		try {
			File file = ResourceUtils.getFile("classpath:20181018-20181018.csv");
			String path = file.getPath();
			List<Map<String, String>> parseExcelCSVFile = CSVUtils.parseExcelCSVFile("GBK", path, null);
			// for (Map<String, String> map : parseExcelCSVFile) {
			// System.out.println(map.toString());
			// }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
