package org.smart4j.framework.util;

public class TextUtil {

	public static void main(String[] args) {
		// 定義要置中的字串
		String text1 = "發送成功";
		String text2 = "1111";
		String text3 = "ABCD";

		// 指定每行的寬度
		int width = 10;

		// 打印置中字串
		System.out.println(centerText(text1, width));
		System.out.println(centerText(text2, width));
		System.out.println(centerText(text3, width));
	}

	public static String centerText(String text, int width) {
		// 計算兩側空格的數量
		int padding = (width - text.length()) / 2;

		// 如果有小數餘數，則在右側多加一個空格
		String format = "%" + (padding + text.length()) + "s" + "%" + padding + "s";

		return String.format(format, text, ""); // 用 String.format 添加空格
	}
}
