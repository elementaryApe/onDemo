package com.udemo.utils.security;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * Desc: AES加密工具
 * User: hansh
 * Date: 2017/12/13
 * Time: 14:37
 */
public class AESUtil {

    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CHARSET = "UTF-8";
    public static final String CIPHER_ALGORITHM = "AES/ECB/NoPadding";

    public AESUtil() {
    }

    public static Key getKey(Integer keySize) throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(keySize.intValue());
        return kg.generateKey();
    }

    public static Key codeToKey(String key) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(key);
        return new SecretKeySpec(keyBytes, "AES");
    }

    public static String decrypt(byte[] data, byte[] key) throws Exception {
        Key k = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(2, k);
        return new String(cipher.doFinal(data), "UTF-8");
    }

    public static String decrypt(String data, String key) throws Exception {
        return decrypt(Base64.decodeBase64(data), Base64.decodeBase64(key));
    }

    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        Key k = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(1, k);
        return cipher.doFinal(data);
    }

    public static String encrypt(String data, String key) throws Exception {
        byte[] dataBytes = data.getBytes("UTF-8");
        byte[] keyBytes = Base64.decodeBase64(key);
        return Base64.encodeBase64String(encrypt(dataBytes, keyBytes));
    }

    public static String getKeyStr(Integer keySize) throws Exception {
        if (keySize == null) {
            keySize = Integer.valueOf(128);
        }

        return Base64.encodeBase64String(getKey(keySize).getEncoded());
    }

    public static class KeySize {
        private static final int SIZE_128 = 128;
        private static final int SIZE_192 = 192;
        private static final int SIZE_256 = 256;

        public KeySize() {
        }
    }
}
