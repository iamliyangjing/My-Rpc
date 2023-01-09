package com.lyj.factory;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: My-Rpc
 * @description: 单例工长
 * @author: lyj
 * @create: 2023-01-10 01:06
 **/
public class SingletonFactory {

    private static final Map<String,Object> OBJECT_MAP = new ConcurrentHashMap<>();

    private SingletonFactory(){}

    public static <T> T getInstance(Class<T> c){
        if (c==null){
            throw new IllegalArgumentException();
        }
        String key = c.toString();
        if (OBJECT_MAP.containsKey(key)){
            return c.cast(OBJECT_MAP.get(key));
        }else {
            return c.cast(OBJECT_MAP.computeIfAbsent(key,k->{
                try {
                    return c.getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }));
        }
    }
}
