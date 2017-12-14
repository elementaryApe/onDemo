package com.udemo.utils;

import java.util.UUID;

/**
 * Desc: UUID
 * User: hansh
 * Date: 2017/12/13
 * Time: 14:40
 */
public class UUIDUtils {
    public UUIDUtils() {
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

}
