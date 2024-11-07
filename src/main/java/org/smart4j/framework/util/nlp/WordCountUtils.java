package org.smart4j.framework.util.nlp;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordCountUtils {

	public static Map<String, Integer> statWords(List<String> words) {
		// 使用 Stream 來統計每個字串出現的次數
		Map<String, Integer> resultMap = words.stream().collect(
				Collectors.groupingBy(str -> str, // map 的 key 是字串本身
				Collectors.collectingAndThen(Collectors.counting(), Long::intValue) // map 的 value 是出現的次數
		));

		// 將結果按 value 大小從大到小排序
		return resultMap.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()) // 根據 value 排序
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, // 如果 key 發生衝突，保留舊的值
						LinkedHashMap::new // 使用 LinkedHashMap 保持插入順序
				));
	}

	public static void main(String[] args) {
		// 測試用例
		List<String> stringList = Arrays.asList("apple", "banana", "apple", "cherry", "banana", "apple");

		// 獲取結果並輸出
		Map<String, Integer> sortedMap = statWords(stringList);
		sortedMap.forEach((key, value) -> System.out.println(key + " -> " + value));
	}
}
