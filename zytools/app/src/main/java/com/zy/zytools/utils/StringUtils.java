package com.zy.zytools.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * desc:
 * author：zy
 * date:2018/5/8
 * time:17:55
 */

public class StringUtils {
    public static boolean isBlank(String str) {
        return (str == null) || (str.trim().length() == 0);
    }

    public static int getStrLen(String value) {
        if (isBlank(value)) {
            return 0;
        }
        int length = 0;
        for (int i = 0; i < value.length(); i++) {
            int ascii = Character.codePointAt(value, i);
            if ((ascii >= 0) && (ascii <= 255))
                length++;
            else {
                length += 2;
            }
        }
        return length;
    }

    public static String cutStr(String value, int count) {
        if (isBlank(value)) {
            return "";
        }
        if (getStrLen(value) <= count) {
            return value;
        }
        String result = "";
        int num = 0;
        for (int i = 0; i < value.length(); i++) {
            String word = value.substring(i, i + 1);
            if (num >= count) {
                break;
            }
            num += getStrLen(word);
            result = result + word;
        }

        return result;
    }

    public static String cutStrEl(String value, int count) {
        if (isBlank(value)) {
            return "";
        }
        if (getStrLen(value) <= count) {
            return value;
        }
        String result = "";
        int num = 0;
        for (int i = 0; i < value.length(); i++) {
            String word = value.substring(i, i + 1);
            if (num >= count - 1) {
                break;
            }
            num += getStrLen(word);
            result = result + word;
        }

        return result + "...";
    }

    //保留两位小数，不四舍五入
    public static String formatDecimal(Object value) {
        if (value == null) {
            return "0.00";
        }
        double v = Double.parseDouble(value.toString());
        if (v == 0) {
            return "0.00";
        }
        final DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(2);
        formater.setGroupingSize(0);
        formater.setRoundingMode(RoundingMode.FLOOR);
        String result = formater.format(v);
        return formatNum(result);
    }

    //保留两位小数，四舍五入
    public static String formatNum(Object object) {
        if (object == null) {
            return "0.00";
        }
        object = ((object instanceof String)) && (isDouble(object.toString())) ? Double.valueOf(Double.parseDouble(object.toString())) : object;
        return new DecimalFormat("0.00").format(object);
    }

    public static boolean isDouble(String str) {
        if (isBlank(str))
            return false;
        try {
            return new Double(Double.parseDouble(str)) != null;
        } catch (Exception e) {
        }
        return false;
    }

    //验证是否中文
    public static boolean validName(String name) {
        String pattern = "[\u4e00-\u9fa5]";
        if (isBlank(name))
            return false;
        return name.matches(pattern);
    }

    //验证身份证号码
    public static boolean validateIdCard(String idCard) {
        if (isBlank(idCard))
            return false;
        String pattern = "^[0-9]{17}[0-9|xX]{1}$";
        return idCard.matches(pattern);
    }

    //验证手机号码
    public static boolean validatePhone(String phone) {
        if (isBlank(phone))
            return false;
        String pattern = "^1[3,4,5,6,8]\\d{9}$";
        return phone.matches(pattern);
    }

    //判断邮编
    public static boolean isZipNO(String zipString) {
        String str = "^[1-9][0-9]{5}$";
        return Pattern.compile(str).matcher(zipString).matches();
    }

    //验证银行卡号
    public static boolean validateBankCard(String bankCard) {
        if (isBlank(bankCard))
            return false;
        String pattern = "^\\d{13,19}$";
        return bankCard.matches(pattern);
    }

    //验证邮箱
    public static boolean validateEmail(String email) {
        if (isBlank(email))
            return false;
        String pattern = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
        return email.matches(pattern);
    }
}
