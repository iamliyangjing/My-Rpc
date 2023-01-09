package com.lyj.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: My-Rpc
 * @description:
 * @author: lyj
 * @create: 2023-01-09 22:06
 **/
@AllArgsConstructor
@Getter
public enum RpcConfigEnum {

    RPC_CONFIG_PATH("rpc.properties"),
    ZK_ADDRESS("rpc.zookeeper.address");

    private final String propertyValue;

}
