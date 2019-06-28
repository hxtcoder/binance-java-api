package com.binance.api.client.util;

import java.lang.reflect.Field;

/**
 * 反射获取
 *
 * @author xiaotian.huang
 * @date 2019-05-17
 */
public class ReflectUtil {

    /** 获取对象中的指定字段 */
    public static <T,S> T getFiledValue(S s, String fieldName, Class<T> tClass) {
        Object value;
        try {
            Field field = s.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            value = field.get(s);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return (T)value;
    }
}
