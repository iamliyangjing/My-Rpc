package com.lyj.exception;

/**
 * @program: My-Rpc
 * @description:
 * @author: lyj
 * @create: 2023-01-09 22:02
 **/
public class SerializeException extends RuntimeException {
    public SerializeException(String message) {
        super(message);
    }
}
