package com.udemo.utils.serialize.adapter;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.udemo.utils.serialize.Serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * Desc: hessian序列化工具
 * User: hansh
 * Date: 2017/12/13
 * Time: 9:57
 */
public class Hessian2Serializer implements Serializer {
    public Hessian2Serializer() {
    }

    public byte[] serialize(Object obj) {
        if (obj == null) {
            return null;
        } else if (!(obj instanceof Serializable)) {
            throw new IllegalArgumentException("object of type [" + obj.getClass().getName() + "] is not serializable");
        } else {
            ByteArrayOutputStream os = null;
            Hessian2Output ho = null;

            try {
                os = new ByteArrayOutputStream();
                ho = new Hessian2Output(os);
                ho.writeObject(obj);
                ho.flush();
                return os.toByteArray();
            } catch (Exception var14) {
                var14.printStackTrace();
            } finally {
                try {
                    if (null != ho) {
                        ho.close();
                    }

                    if (null != os) {
                        os.close();
                    }
                } catch (Exception var13) {
                    var13.printStackTrace();
                }

            }

            return null;
        }
    }

    public Object deserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        } else {
            ByteArrayInputStream is = null;
            Hessian2Input hi = null;

            try {
                is = new ByteArrayInputStream(bytes);
                hi = new Hessian2Input(is);
                return hi.readObject();
            } catch (Exception var14) {
                var14.printStackTrace();
            } finally {
                try {
                    if (null != hi) {
                        hi.close();
                    }

                    if (null != is) {
                        is.close();
                    }
                } catch (Exception var13) {
                    var13.printStackTrace();
                }

            }

            return null;
        }
    }

}
