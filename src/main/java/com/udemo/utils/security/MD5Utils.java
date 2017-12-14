package com.udemo.utils.security;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Desc: MD5
 * User: hansh
 * Date: 2017/12/13
 * Time: 14:39
 */
public class MD5Utils {
    private static final String KEY_ALGORITHM = "MD5";
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String[] hexDigits = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public MD5Utils() {
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (b < 0) {
            n = 256 + b;
        }

        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        byte[] var2 = b;
        int var3 = b.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            byte aB = var2[var4];
            resultSb.append(byteToHexString(aB));
        }

        return resultSb.toString().toUpperCase();
    }

    public static String md5DigestStr(String content) {
        if (StringUtils.isBlank(content)) {
            return null;
        } else {
            try {
                return md5Digest(content.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException var2) {
                return null;
            }
        }
    }

    public static String md5Digest(byte[] src) {
        MessageDigest alg;
        try {
            alg = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var3) {
            return null;
        }

        return byteArrayToHexString(alg.digest(src));
    }

    public static String sign(String content) {
        return sign(content, "");
    }

    public static String sign(String content, String md5Key) {
        content = content + "&key=" + md5Key;

        try {
            return md5Digest(content.getBytes("UTF-8"));
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

}
