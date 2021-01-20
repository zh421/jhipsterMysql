package aiot.java.util;

import java.util.regex.Pattern;

/**
 * 檢查合法URL邏輯共用
 *
 * @author karta
 */
public class CsvUtils {

    /**
     * 轉換字串為合法CSV格式
     *
     * @param str 欄位字串
     * @return 欄位字串
     */
    public static String csvString(String str) {
        if (str != null && str.length() >= 2) {
            if (str.indexOf("\"") == 0) str = str.substring(1, str.length());   //去掉第一個 "
            if (str.lastIndexOf("\"") == (str.length() - 1)) str = str.substring(0, str.length() - 1);  //去掉最後一個 "

            str = str.replaceAll("\"\"", "\"");//把兩個雙引號換成一個雙引號
        }
        return null == str ? "" : str;
    }
}
