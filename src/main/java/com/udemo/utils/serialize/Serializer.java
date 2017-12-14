package com.udemo.utils.serialize;

/**
 * Desc: 序列化工具
 * User: hansh
 * Date: 2017/12/12
 * Time: 18:09
 */
public interface Serializer {

    /**
     * 序列化
     */
    byte[] serialize(Object var1);

    /**
     * 反序列化
     */
    Object deserialize(byte[] var1);

}
