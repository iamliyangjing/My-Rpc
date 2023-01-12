package com.lyj.registry;

import com.lyj.extension.SPI;

import java.net.InetSocketAddress;

/**
 * @program: My-Rpc
 * @description:
 * @author: lyj
 * @create: 2023-01-11 21:37
 **/
@SPI
public interface ServiceRegistry {
    /**
     * register service
     * @param rpcServiceName
     * @param inetSocketAddress
     */
    void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress);
}
