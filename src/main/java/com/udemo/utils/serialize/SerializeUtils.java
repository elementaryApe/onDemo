package com.udemo.utils.serialize;

import com.udemo.utils.serialize.adapter.Hessian2Serializer;

/**
 * Desc: 序列化工具
 * User: hansh
 * Date: 2017/12/13
 * Time: 9:53
 */
public class SerializeUtils {

    public static Serializer serializer;

    public SerializeUtils() {
    }

    public static byte[] serialize(Object obj) {
        return serializer.serialize(obj);
    }

    public static Object deserialize(byte[] bytes) {
        return serializer.deserialize(bytes);
    }

    static {
        if (serializer == null) {
            serializer = new Hessian2Serializer();
        }

    }
}
