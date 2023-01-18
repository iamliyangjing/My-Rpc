package com.lyj.remoting.transport;

import com.lyj.extension.SPI;
import com.lyj.remoting.dto.RpcRequest;

/**
 * @program: My-Rpc
 * @description: send RpcRequest。
 * @author: lyj
 * @create: 2023-01-12 15:25
 **/
@SPI
public interface RpcRequestTransport {
    /**
     * send rpc request to server and get result
     * @param rpcRequest
     * @return data from server
     */
    Object sendRpcRequest(RpcRequest rpcRequest);
}
