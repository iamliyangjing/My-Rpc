package com.lyj.remoting.transport.netty.client;

import com.lyj.remoting.dto.RpcResponse;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: My-Rpc
 * @description: unprocessed requests by the server.
 * @author: lyj
 * @create: 2023-01-12 16:36
 **/
public class UnprocessedRequests {

    private static final Map<String, CompletableFuture<RpcResponse<Object>>> UNPROCESSED_RESPONSE_FUTURES = new ConcurrentHashMap<>();

    /**
     * 存放为完成的请求
     * @param requestId
     * @param future
     */
    public void put(String requestId,CompletableFuture<RpcResponse<Object>> future){
        UNPROCESSED_RESPONSE_FUTURES.put(requestId,future);
    }

    public void complete(RpcResponse<Object> rpcResponse){
        CompletableFuture<RpcResponse<Object>> future = UNPROCESSED_RESPONSE_FUTURES.remove(rpcResponse.getRequestId());
        if (null!=future){
            future.complete(rpcResponse);
        }else {
            throw new IllegalStateException();
        }
    }

}
