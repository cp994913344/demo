package com.ljs.demo.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChineseCharactorUtils {

    // 判断一个字符是否是中文
    public static boolean isChinese(char c) {
        return c >= 0x4E00 && c <= 0x9FA5;// 根据字节码判断
    }

    // 判断一个字符串是否含有中文
    public static boolean isChinese(String str) {
        if (str == null) return false;
        for (char c : str.toCharArray()) {
            if (isChinese(c)) return true;// 有一个中文字符就返回
        }
        return false;
    }

    //判断是否是身份证号
    private boolean personNum(String str) {
        Pattern pattern = Pattern.compile("(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }


    //判断是否是纯数字
    private boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断字符串是否包含数字
     */
    private boolean containsNum(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        for (int i = 0; i < str.length(); i++) { //循环遍历字符串
            if (Character.isDigit(str.charAt(i))) {     //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            }
        }
        return isDigit;
    }
}
