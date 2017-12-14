package com.udemo.utils.security;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;

/**
 * Desc: DES
 * User: hansh
 * Date: 2017/12/13
 * Time: 14:39
 */
public class DESUtils {
    private static final byte[] DEFAULT_KEYIV = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};

    public DESUtils() {
    }

    public static String des3EncodeECB(String key, String data) throws Exception {
        if (!StringUtils.isBlank(key) && !StringUtils.isBlank(data)) {
            byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(key);
            byte[] bytes = des3EncodeECB(keyBytes, data.getBytes("UTF-8"));
            return (new BASE64Encoder()).encode(bytes);
        } else {
            return null;
        }
    }

    public static byte[] des3EncodeECB(byte[] key, byte[] data) throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        Key deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/ECB/PKCS5Padding");
        cipher.init(1, deskey);
        return cipher.doFinal(data);
    }

    public static String des3DecodeECB(String key, String data) throws Exception {
        if (!StringUtils.isBlank(key) && !StringUtils.isBlank(data)) {
            byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(key);
            byte[] dataBytes = (new BASE64Decoder()).decodeBuffer(data);
            byte[] bytes = des3DecodeECB(keyBytes, dataBytes);
            return new String(bytes, "UTF-8");
        } else {
            return null;
        }
    }

    public static byte[] des3DecodeECB(byte[] key, byte[] data) throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        Key deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/ECB/PKCS5Padding");
        cipher.init(2, deskey);
        return cipher.doFinal(data);
    }

    public static String des3EncodeCBC(String key, String keyIV, String data) throws Exception {
        byte[] keyIVBytes = DEFAULT_KEYIV;
        if (!StringUtils.isBlank(key) && !StringUtils.isBlank(data)) {
            if (StringUtils.isNotBlank(keyIV)) {
                keyIVBytes = keyIV.getBytes();
            }

            byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(key);
            byte[] bytes = des3EncodeCBC(keyBytes, keyIVBytes, data.getBytes("UTF-8"));
            return (new BASE64Encoder()).encode(bytes);
        } else {
            return null;
        }
    }

    public static byte[] des3EncodeCBC(byte[] key, byte[] keyIV, byte[] data) throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        Key deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(keyIV);
        cipher.init(1, deskey, ips);
        return cipher.doFinal(data);
    }

    public static String des3DecodeCBC(String key, String keyIV, String data) throws Exception {
        byte[] keyIVBytes = DEFAULT_KEYIV;
        if (!StringUtils.isBlank(key) && !StringUtils.isBlank(data)) {
            if (StringUtils.isNotBlank(keyIV)) {
                keyIVBytes = keyIV.getBytes();
            }

            byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(key);
            byte[] dataBytes = (new BASE64Decoder()).decodeBuffer(data);
            byte[] bytes = des3DecodeCBC(keyBytes, keyIVBytes, dataBytes);
            return new String(bytes, "UTF-8");
        } else {
            return null;
        }
    }

    public static byte[] des3DecodeCBC(byte[] key, byte[] keyIV, byte[] data) throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        Key deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(keyIV);
        cipher.init(2, deskey, ips);
        return cipher.doFinal(data);
    }
}
