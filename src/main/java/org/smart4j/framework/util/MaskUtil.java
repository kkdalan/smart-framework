package org.smart4j.framework.util;

public class MaskUtil {

    public static String maskMobileNumber(String mobileNumber) {
	return mask(mobileNumber, 4, 3, '*');
    }

    public static String mask(String text, int start, int length) {
	return mask(text, start, length, '*');
    }

    /**
     * 字串遮罩
     * 
     * @param text       原始字串
     * @param start      遮罩起始位置index
     * @param length     遮罩長度
     * @param maskSymbol 遮罩符號
     * @return 遮罩過的字串
     */
    public static String mask(String text, int start, int length, char maskSymbol) {
	if (text == null || text.isEmpty()) {
	    return "";
	}
	if (start < 0) {
	    start = 0;
	}
	if (length < 1) {
	    return text;
	}

	StringBuilder sb = new StringBuilder();
	char[] cc = text.toCharArray();
	for (int i = 0; i < cc.length; i++) {
	    if (i >= start && i < (start + length)) {
		sb.append(maskSymbol);
	    } else {
		sb.append(cc[i]);
	    }
	}
	return sb.toString();
    }

    public static void main(String[] args) {

	String id = "A123456789";
	System.out.println(MaskUtil.mask(id, 2, 5, '*')); // A1*****789
	System.out.println(MaskUtil.mask(id, 5, 10, '*')); // A1234*****
	System.out.println(MaskUtil.mask(id, -1, 4, '#')); // ####456789

	String mobileNumber = "0912111000";
	System.out.println(MaskUtil.maskMobileNumber(mobileNumber)); // 0912***000

    }
}
