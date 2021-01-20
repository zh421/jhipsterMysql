package aiot.java.util;

public class StringTrimUtils {

    public static String limit1000Size(String sourceStr) {
        return LiaStringUtils.trimString(sourceStr, 1000);
    }

    public static String limit200Size(String sourceStr) {
        return LiaStringUtils.trimString(sourceStr, 200);
    }

    public static String limit10Size(String sourceStr) {
        return LiaStringUtils.trimString(sourceStr, 10);
    }
}
