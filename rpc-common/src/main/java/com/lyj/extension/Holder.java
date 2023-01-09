package com.lyj.extension;

/**
 * @program: My-Rpc
 * @description:
 * @author: lyj
 * @create: 2023-01-09 22:35
 **/
public class Holder<T> {

    private volatile T value;

    public T get(){
        return value;
    }

    public void set(T value){
        this.value = value ;
    }
}
