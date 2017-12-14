package com.udemo.utils.security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

/**
 * Desc:
 * User: hansh
 * Date: 2017/12/13
 * Time: 14:39
 */
public class Coder {

    private static final String KEY_SHA = "SHA";

    public Coder() {
    }

    public static String decryptBASE64ToStr(String key) throws Exception {
        return new String((new BASE64Decoder()).decodeBuffer(key), "UTF-8");
    }

    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    public static String encryptBASE64(byte[] bytes) throws Exception {
        return (new BASE64Encoder()).encode(bytes);
    }

    public static String encryptStrToBASE64(String str) throws Exception {
        return (new BASE64Encoder()).encode(str.getBytes("UTF-8"));
    }

    public static String encryptMD5(String str) throws Exception {
        return MD5Utils.sign(str);
    }

    public static byte[] encryptSHA(byte[] data) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA");
        sha.update(data);
        return sha.digest();
    }

    public static String asHex(byte[] buf) {
        StringBuilder strbuf = new StringBuilder(buf.length * 2);

        for(int i = 0; i < buf.length; ++i) {
            if ((buf[i] & 255) < 16) {
                strbuf.append("0");
            }

            strbuf.append(Long.toString((long)(buf[i] & 255), 16));
        }

        return strbuf.toString();
    }
}
