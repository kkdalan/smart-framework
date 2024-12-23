package org.smart4j.framework.util;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class PrintUtil {

	public static void printAsTable(List<String> headers, List<List<String>> rows) {
		System.out.println(formatAsTable(headers, rows));
	}

	private static String formatAsTable(List<String> headers, List<List<String>> rows) {
		
		int[] maxLengths = new int[rows.get(0).size()];
		for (List<String> row : rows) {
			for (int i = 0; i < row.size(); i++) {
				maxLengths[i] = Math.max(maxLengths[i], headers.get(i).length());
				maxLengths[i] = Math.max(maxLengths[i], row.get(i).length());
			}
		}

		StringBuilder result = new StringBuilder();
		
		// Build row format
		int space = 2;
		StringBuilder formatBuilder = new StringBuilder();
		for (int maxLength : maxLengths) {
			formatBuilder.append("%-").append(maxLength + space).append("s");
		}
		String format = formatBuilder.toString();
	
		// Make line
		int headerLineLength = Arrays.stream(maxLengths).map(maxLength -> maxLength + space).sum();
		String headerLine = StringUtils.repeat("-", headerLineLength);
	
		// Append header with lines
		result.append(headerLine).append("\n");
		result.append(String.format(format, headers.toArray(new String[0]))).append("\n");
		result.append(headerLine).append("\n");
		
		// Append data rows
		for (List<String> row : rows) {
			result.append(String.format(format, row.toArray(new String[0]))).append("\n");
		}
		
		// Aappend line
		result.append(headerLine).append("\n");
		
		return result.toString();
	}

}
