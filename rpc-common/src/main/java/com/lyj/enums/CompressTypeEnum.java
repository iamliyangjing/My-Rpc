package com.lyj.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: My-Rpc
 * @description: 压缩枚举类
 * @author: lyj
 * @create: 2023-01-09 22:03
 **/
@AllArgsConstructor
@Getter
public enum CompressTypeEnum {

    GIZP((byte)0x01,"gizp");

    private final byte code;

    private final String name;

    public static String getName(byte code){
        for (CompressTypeEnum c : CompressTypeEnum.values()) {
            if (c.getCode()==code){
                return c.name;
            }
        }
        return null;
    }
}
