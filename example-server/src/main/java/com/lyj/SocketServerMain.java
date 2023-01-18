package com.lyj;

import com.lyj.config.RpcServiceConfig;
import com.lyj.remoting.transport.server.SocketRpcServer;
import com.lyj.serviceimpl.HelloServiceImpl;

/**
 * @program: My-Rpc
 * @description:
 * @author: lyj
 * @create: 2023-01-13 10:52
 **/
public class SocketServerMain {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        SocketRpcServer socketRpcServer = new SocketRpcServer();
        RpcServiceConfig rpcServiceConfig = new RpcServiceConfig();
        rpcServiceConfig.setService(helloService);
        socketRpcServer.registerService(rpcServiceConfig);
        socketRpcServer.start();
    }
}
