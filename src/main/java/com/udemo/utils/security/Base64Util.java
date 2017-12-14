package com.udemo.utils.security;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Desc: BASE64
 * User: hansh
 * Date: 2017/12/13
 * Time: 14:38
 */
public class Base64Util {
    private static final String DEFAULT_CHARSET = "UTF-8";

    public Base64Util() {
    }

    public static String getBASE64(String s) {
        if (s == null) {
            return null;
        } else {
            try {
                return getBASE64(s.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException var2) {
                var2.printStackTrace();
                return null;
            }
        }
    }

    public static String getBASE64(byte[] b) {
        byte[] rb = Base64.encodeBase64(b);
        if (rb == null) {
            return null;
        } else {
            try {
                return new String(rb, "UTF-8");
            } catch (UnsupportedEncodingException var3) {
                var3.printStackTrace();
                return null;
            }
        }
    }

    public static String getFromBASE64(String s) {
        if (s == null) {
            return null;
        } else {
            try {
                byte[] e = getBytesBASE64(s);
                return e == null ? null : new String(e, "UTF-8");
            } catch (UnsupportedEncodingException var2) {
                var2.printStackTrace();
                return null;
            }
        }
    }

    public static byte[] getBytesBASE64(String s) {
        if (s == null) {
            return null;
        } else {
            try {
                return Base64.decodeBase64(s.getBytes("UTF-8"));
            } catch (Exception var2) {
                var2.printStackTrace();
                return null;
            }
        }
    }

}
