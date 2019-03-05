package com.github.dreamroute;

/**
 * 
 * @author w.dehai
 *
 */
public class StringUtil {

    private StringUtil() {}

    /**
     * 驼峰命名转为下划线命名
     * 
     * @param param
     * @return 返回下划线
     */
    public static String HumpToUnderline(String param) {
        StringBuilder sb = new StringBuilder(param);
        int temp = 0;// 定位
        if (!param.contains("_")) {
            for (int i = 0; i < param.length(); i++) {
                if (Character.isUpperCase(param.charAt(i))) {
                    sb.insert(i + temp, "_");
                    temp += 1;
                }
            }
        }
        return sb.toString().toUpperCase();
    }

    /***
     * 下划线命名转为驼峰命名
     * 
     * @param param 下划线命名的字符串
     */

    public static String UnderlineToHump(String param) {
        StringBuilder result = new StringBuilder();
        String a[] = param.split("_");
        for (String s : a) {
            if (!param.contains("_")) {
                result.append(s);
                continue;
            }
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

}
