package com.lyj.loadbalance;

import com.lyj.extension.SPI;
import com.lyj.remoting.dto.RpcRequest;

import java.util.List;

/**
 * @program: My-Rpc
 * @description: Interface to the load balancing policy
 * @author: lyj
 * @create: 2023-01-12 10:32
 **/
@SPI
public interface LoadBalance {
    /**
     * Choose one from the list of existing service address list
     * @param serviceUrlList
     * @param rpcRequest
     * @return
     */
    String selectServiceAddress(List<String> serviceUrlList, RpcRequest rpcRequest);
}
