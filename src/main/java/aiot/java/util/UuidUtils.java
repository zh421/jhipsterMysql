package aiot.java.util;

import org.apache.commons.text.RandomStringGenerator;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.UUID;

/** 基於 base64 編碼縮短後的 uuid */
public class UuidUtils {

    public static String randomId() {
        Encoder encoder = Base64.getUrlEncoder();
        // Create random UUID
        UUID uuid = UUID.randomUUID();

        // Create byte[] for base64 from uuid
        byte[] src =
            ByteBuffer.wrap(new byte[16])
                .putLong(uuid.getMostSignificantBits())
                .putLong(uuid.getLeastSignificantBits())
                .array();

        // Encode to Base64 and remove trailing ==
        return encoder.encodeToString(src).substring(0, 22);
    }

    public static String randomId_7() {
        Encoder encoder = Base64.getUrlEncoder();
        // Create random UUID
        UUID uuid = UUID.randomUUID();

        // Create byte[] for base64 from uuid
        byte[] src =
            ByteBuffer.wrap(new byte[16])
                .putLong(uuid.getMostSignificantBits())
                .putLong(uuid.getLeastSignificantBits())
                .array();

        // 八碼
        return encoder.encodeToString(src).substring(0, 7);
    }

    public static String randomWithDateAndId() {
        StringBuilder strb = new StringBuilder();
        strb.append(DateUtils.getDate("yyyyMMddHHmmss"));
        strb.append("-");
        strb.append(getRandomString(7));
        return strb.toString();
    }

    public static String randomWithDateAndId(int length) {
        StringBuilder strb = new StringBuilder();
        strb.append(DateUtils.getDate("yyyyMMddHHmmss"));
        strb.append("-");
        strb.append(getRandomString(length));
        return strb.toString();
    }

    public static String getToStr(String linkId) {
        int strLeng = linkId.length();
        return linkId.substring(strLeng - 3, strLeng);
    }

    public static String getFromStr(String linkId) {
        int strLeng = linkId.length();
        return linkId.substring(strLeng - 7, strLeng - 7 + 3);
    }

    public static String getCaseIdStr(String linkId) {
        int strLeng = linkId.length();
        return linkId.substring(0, strLeng - 8);
    }

    public static String getRandomString(int length) {

        char[][] pairs = { { 'a', 'z' }, { 'A', 'Z' }, { '0', '9' } };
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange(pairs).build();
        String randomString = generator.generate(length);
        return randomString;

    }
}
