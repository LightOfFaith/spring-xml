package com.share.lifetime.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 
 * @author liaoxiang
 * @see http://commons.apache.org/proper/commons-csv/
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CSVUtils {

	/**
	 * @see java.nio.charset.StandardCharsets
	 */
	private static final String DEFAULT_CHARSET = StandardCharsets.UTF_8.name();//

	/**
	 * 
	 * @param fileName 读取文件的名称 "path/to/file.csv"
	 * @return
	 * @throws IOException
	 */
	public static List<Map<String, String>> parseExcelCSVFile(final String fileName) throws IOException {
		return parseExcelCSVFile(DEFAULT_CHARSET, fileName);
	}

	/**
	 * 
	 * @param charsetName 文件字符集编码
	 * @param fileName    读取文件的名称 "path/to/file.csv"
	 * @return
	 * @throws IOException
	 */
	public static List<Map<String, String>> parseExcelCSVFile(final String charsetName, final String fileName)
			throws IOException {
		return parseExcelCSVFile(charsetName, fileName, new String[] {});
	}

	/**
	 * 
	 * @param charsetName 文件字符集编码
	 * @param fileName    读取文件的名称 "path/to/file.csv"
	 * @param header      标题，null则禁用，如果为empty，则自动解析，否则用户指定规则
	 * @return
	 * @throws IOException
	 */
	public static List<Map<String, String>> parseExcelCSVFile(final String charsetName, final String fileName,
			final String... header) throws IOException {
		List<CSVRecord> records = null;
		Map<String, String> record2Map = null;
		List<Map<String, String>> result = null;
		// Reader in = new FileReader(fileName);
		Reader in = new InputStreamReader(new FileInputStream(fileName), charsetName);
		CSVFormat excel = CSVFormat.EXCEL;
		if (header != null && header.length > 0) {
			records = excel.withHeader(header).parse(in).getRecords();
			result = new ArrayList<Map<String, String>>(records.size());
			for (CSVRecord record : records) {
				record2Map = record.toMap();
				result.add(record2Map);
			}
		} else {
			records = excel.parse(in).getRecords();
			result = new ArrayList<Map<String, String>>(records.size());
			for (CSVRecord record : records) {
				int size = record.size();
				for (int i = 0; i < size; i++) {
					System.out.print(record.get(i) + "]");
				}
				System.out.println();
			}
		}

		return result;
	}

}
