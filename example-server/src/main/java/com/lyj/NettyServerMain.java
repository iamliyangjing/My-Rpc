package com.lyj;

import com.lyj.annotation.RpcScan;
import com.lyj.config.RpcServiceConfig;
import com.lyj.remoting.transport.netty.server.NettyRpcServer;
import com.lyj.serviceimpl.HelloServiceImpl2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @program: My-Rpc
 * @description:
 * @author: lyj
 * @create: 2023-01-13 10:51
 **/
@RpcScan(basePackage = {"com.lyj"})
public class NettyServerMain {
    public static void main(String[] args) {
        // Register service via annotation
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(NettyServerMain.class);
        NettyRpcServer nettyRpcServer = (NettyRpcServer) applicationContext.getBean("nettyRpcServer");
        // Register service manually
        HelloService helloService2 = new HelloServiceImpl2();
        RpcServiceConfig rpcServiceConfig = RpcServiceConfig.builder()
                .group("test2").version("version2").service(helloService2).build();
        nettyRpcServer.registerService(rpcServiceConfig);
        nettyRpcServer.start();
    }
}
