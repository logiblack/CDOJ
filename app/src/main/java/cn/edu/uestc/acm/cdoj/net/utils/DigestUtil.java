package cn.edu.uestc.acm.cdoj.net.utils;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by leigu on 2017/4/20.
 */

public class DigestUtil {
    private static String TAG = "DigestUtil";

    public static String digest(String input, String algorithm) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Log.e(TAG, "No Such Algorithm");
            return input;
        }
        return byteArray2Hex(digest.digest(input.getBytes()));
    }

    public static String md5(String input) {
        return digest(input, "MD5");
    }

    public static String sha1(String input) {
        return digest(input, "SHA-1");
    }

    public static String sha256(String input) {
        return digest(input, "SHA-256");
    }

    public static String byteArray2Hex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte x : bytes) {
            stringBuilder.append(String.format("%X", x & 0xF0)).append(String.format("%X", x & 0xF));
        }
        return stringBuilder.toString();
    }
}
