
package com.rolmex.android.rolplayer.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private final static Pattern emailer = Pattern
            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    private final static SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final static SimpleDateFormat dateFormater2 = new SimpleDateFormat("yyyy年MM月dd日");

    private final static SimpleDateFormat dateFormater3 = new SimpleDateFormat("yyyyMMddHHmmss");
    
    private final static SimpleDateFormat dateFormater4 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private final static DecimalFormat df = new DecimalFormat("0.00");
    private final static DecimalFormat df4 = new DecimalFormat("0.0000");
    
    

    /**
     * 把字符串转换成时间
     * 
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.parse(sdate);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getDateFormat(Date date) {
        return dateFormater2.format(date);
    }

    /**
     * 日期转换成String字符串
     */
    public static String getDate() {
        return dateFormater3.format(System.currentTimeMillis());
    }

    public static String getDate(Date date) {
        return dateFormater3.format(date);

    }

    public static String getDateNormal(Date date) {
        return dateFormater.format(date);
    }
    public static String getDateNormal4(Date date){
        return dateFormater4.format(date);
    }

    /**
     * 四位小数转成两位
     * 
     * @param bd
     * @return
     */
    public static String getTwoFormat(BigDecimal bd) {
        
          String s =  df4.format(bd).toString();
          return s.substring(0, s.length()-2);

    }
    public static String getTwoFormat(String ss){
        if(ss.length()>4)
        return ss.substring(0, ss.length()-2);
        else
            return ss;
    }

    /**
     * 字符串转Int
     * 
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return defValue;
        }
    }

    public static double toDouble(String str, double defValue) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return defValue;
        }
    }

    /**
     * Object 转化int
     * 
     * @param obj
     * @return
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * Object 转换成Long
     * 
     * @param obj
     * @return
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {

        }
        return 0;
    }

    /**
     * String 转换成boolean
     * 
     * @param b
     * @return
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
        }
        ;
        return false;
    }

    /**
     * 判断是否为空
     * 
     * @param input
     * @return
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input)||input.equals("null"))
            return true;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    public static String streamToString(InputStream is) throws IOException {
        String msg = "";
        if (is != null) {
            StringBuilder builder = new StringBuilder();
            // BufferenReader reader = new BufferedReader(new InputStreamReader)
        }
        return msg;
    }

    /**
     * 判断是否全部为数字
     * 
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否为Money
     * 
     * @param str
     * @return
     */
    public static boolean isMoney(String str) {
        Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]{1,2}");
        Matcher isMoney = pattern.matcher(str);
        if (!isMoney.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否为银行账号
     * 
     * @param str
     * @return
     */
    public static boolean isAccount(String str) {
        Pattern pattern = Pattern.compile("[0-9]{16}|[0-9]{19}");
        Matcher isAccount = pattern.matcher(str);
        if (!isAccount.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否为身份证号
     * 
     * @param str
     * @return
     */
    public static boolean isIDCard(String str) {
        Pattern pattern = Pattern.compile("[0-9]{15}|[0-9]{18}|[0-9]{17}(\\d|X|x)");
        Matcher isIDcard = pattern.matcher(str);
        if (!isIDcard.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否为电话号码
     * 
     * @param str
     * @return
     */
    public static boolean isPhoneNum(String str) {
        Pattern pattern = Pattern.compile("^1[0-9]{10}|[0-9]{3,4}\\-[0-9]{7,8}");
        Matcher isPhoneNum = pattern.matcher(str);
        if (!isPhoneNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 返回是否为邮编
     * 
     * @param str
     * @return
     */
    public static boolean isZipCode(String str) {
        Pattern pattern = Pattern.compile("[0-9]{6}");
        Matcher isZip = pattern.matcher(str);
        if (!isZip.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 是否为邮箱
     * 
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 判断密码长度是否正确
     * 
     * @param s
     * @return
     */
    public static boolean isLengthTrue(String s) {
        int length = s.length();
        if (length >= 6 && length <= 20)
            return true;
        return false;
    }

    /**
     * 判断是否为手机号码
     * 
     * @param str
     * @return
     */
    public static boolean isMobile(String str) {
        Pattern pattern = Pattern.compile("^1[0-9]{10}");
        Matcher isMobile = pattern.matcher(str);
        if (!isMobile.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否为2-10为的网名
     * 
     * @param str
     * @return
     */
    public static boolean isNetName(String str) {
        if (!(str.length() > 1 && str.length() < 11))
            return false;
        return true;
    }

    /**
     * 判断是否为2-20为的用户名
     * 
     * @param str
     * @return
     */
    public static boolean isOpenName(String str) {
        if (!(str.length() > 1 && str.length() < 21))
            return false;
        return true;
    }

    /**
     * 判断为2-50为的地址
     * 
     * @param str
     * @return
     */
    public static boolean isAddress(String str) {
        if (!(str.length() > 1 && str.length() < 51))
            return false;
        return true;
    }

    /**
     * 判断是否存在非法字符
     * 
     * @param str
     * @return
     */
    public static boolean isContantIlCha(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ' ' || c == '\t' || c == '\r' || c == '\n') {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否包含特殊字符
     * 
     * @param s
     * @return
     */
    public static boolean isContainSpecal(String s) {
        String str = "[`~!@#$%^&*()=|{}':;',\\[\\].<>?/~！@#￥……&*（）——|{}【】‘；：”“'。，、？]";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(s);
        return m.find();
    }

    /**
     * 是否包含字母
     * 
     * @param s
     * @return
     */
    public static boolean isContainChar(String s) {
        String str = "[a-zA-Z]";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(s);
        return m.find();
    }

    /**
     * 是否包含数字
     */
    public static boolean isContainNum(String s) {
        String str = "[0-9]";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(s);
        return m.find();
    }

    /**
     * 返回两位小数部分
     * 
     * @return
     */
    public static String getRandomStrimg() {
        String msg = ".";
        msg += (int)(Math.random() * 100);
        return msg;
    }

    public static String getString(Context context, int id) {
        return context.getResources().getString(id);
    }
}
