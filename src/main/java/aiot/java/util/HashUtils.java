package aiot.java.util;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.common.hash.Hashing;
import com.google.common.io.Files;

import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.org.apache.commons.lang3.exception.ExceptionUtils;

@Slf4j
public class HashUtils {

    public static String fileToHash(String filePath) throws IOException {

        File sf = new File(filePath);
        if (!sf.exists()) {
            // 代表檔案存在
            throw new RuntimeException("檔案" + filePath + "不存在");
        }

        return Files.asByteSource(sf).hash(Hashing.sha512()).toString();

    }

    public static String fileToHash(File sf) throws IOException {
        if (!sf.exists()) {
            // 代表檔案存在
            throw new RuntimeException("檔案" + sf.getAbsolutePath() + "不存在");
        }
        return Files.asByteSource(sf).hash(Hashing.sha512()).toString();
    }



    public static String strToHash(String data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        digest.reset();
        digest.update(data.getBytes(StandardCharsets.UTF_8));
        return String.format("%0128x", new BigInteger(1, digest.digest()));
    }

    public static String sha256Hex(String text) {
        return DigestUtils.sha256Hex(text);
    }

    public static String sha256Hex(byte[] data) {
        return DigestUtils.sha256Hex(data);
    }

    // public static String sha512Hex(String text) {
    //
    // return DigestUtils.sha512Hex(text);
    // //
    // // String toReturn = null;
    // // try {
    // // MessageDigest digest = MessageDigest.getInstance("SHA-512");
    // // digest.reset();
    // // digest.update(input.getBytes(StandardCharsets.UTF_8));
    // // toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
    // // } catch (Exception e) {
    // // e.printStackTrace();
    // // }
    // //
    // // return toReturn;
    // }

}
