package com.lyj.config;

import com.lyj.registry.zk.util.CuratorUtils;
import com.lyj.remoting.transport.netty.server.NettyRpcServer;
import com.lyj.utils.concurrent.threadpool.ThreadPoolFactoryUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * @program: My-Rpc
 * @description:
 * @author: lyj
 * @create: 2023-01-12 15:14
 **/
@Slf4j
public class CustomShutdownHook {

    private static final CustomShutdownHook CUSTOM_SHUTDOWN_HOOK = new CustomShutdownHook();

    public static CustomShutdownHook getCustomShutdownHook(){
        return CUSTOM_SHUTDOWN_HOOK;
    }

    public void clearAll(){
        log.info("addShutdownHook for clearAll");
        // add shutdownhook at shutdown doing
        // new thread to async
        // 在 Java 虚拟机关闭时注册一个线程，该线程通常用于执行清理活动，如关闭文件或释放资源。这个方法可以在程序正常终止或异常终止的时候调用，以便进行一些额外的清理工作。
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            try {
                InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost().getHostAddress(), NettyRpcServer.PORT);
                CuratorUtils.clearRegistry(CuratorUtils.getZkClient(), inetSocketAddress);
            } catch (UnknownHostException ignored) {
            }
            ThreadPoolFactoryUtil.shutDownAllThreadPool();
        }));
    }
}
