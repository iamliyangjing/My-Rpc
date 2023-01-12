package com.lyj.registry;

import com.lyj.extension.SPI;
import com.lyj.remoting.dto.RpcRequest;

import java.net.InetSocketAddress;

/**
 * @program: My-Rpc
 * @description: service dicovery
 * @author: lyj
 * @create: 2023-01-11 21:36
 **/
@SPI
public interface ServiceDiscovery {
    /**
     *  服务发现
     * @param rpcRequest
     * @return
     */
    InetSocketAddress lookupService(RpcRequest rpcRequest);
}
