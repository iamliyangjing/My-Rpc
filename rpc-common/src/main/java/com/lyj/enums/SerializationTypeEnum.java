package com.lyj.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: My-Rpc
 * @description:
 * @author: lyj
 * @create: 2023-01-09 22:07
 **/
@AllArgsConstructor
@Getter
public enum SerializationTypeEnum {


    KYRO((byte) 0x01, "kyro"),

    PROTOSTUFF((byte) 0x02, "protostuff"),

    HESSIAN((byte) 0X03, "hessian");

    private final byte code;

    private final String name;

    public static String getName(byte code){
        for (SerializationTypeEnum c : SerializationTypeEnum.values()) {
            if (c.getCode()==code){
                return c.name;
            }
        }
        return null;
    }
}