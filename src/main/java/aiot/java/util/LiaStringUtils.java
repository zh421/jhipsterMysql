package aiot.java.util;

import java.util.Optional;
import java.util.regex.Pattern;

public class LiaStringUtils {

    public static String trimString(String str, int length) {
        if (str == null) {
            return null;
        }
        return str.substring(0, Math.min(str.length(), length));
    }

    //把區塊鏈可能會有問題的字串替換
    public static String formatTOBCStr(String str) {
        if (str == null) {
            return null;
        }
        // base64 url 轉成base64
        return str.replace('_', '/');
    }

    public static String formatFromBCStr(String str) {
        if (str == null) {
            return null;
        }
        // base64 url 轉成base64
        return str.replace('/', '_');
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static int len(String str) {

        return get(str).length();
    }

    public static String get(String str) {

        return Optional.ofNullable(str).orElse("");
    }

}
