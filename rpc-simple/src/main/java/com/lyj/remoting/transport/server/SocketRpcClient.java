package com.lyj.remoting.transport.server;

import com.lyj.exception.RpcException;
import com.lyj.extension.ExtensionLoader;
import com.lyj.registry.ServiceDiscovery;
import com.lyj.remoting.dto.RpcRequest;
import com.lyj.remoting.transport.RpcRequestTransport;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @program: My-Rpc
 * @description:
 * @author: lyj
 * @create: 2023-01-12 15:43
 **/
@AllArgsConstructor
@Slf4j
public class SocketRpcClient implements RpcRequestTransport {

    private final ServiceDiscovery serviceDiscovery;

    public SocketRpcClient(){
        this.serviceDiscovery= ExtensionLoader.getExtensionLoader(ServiceDiscovery.class).getExtension("zk");
    }

    @Override
    public Object sendRpcRequest(RpcRequest rpcRequest) {
        InetSocketAddress inetSocketAddress = serviceDiscovery.lookupService(rpcRequest);
        try (Socket socket = new Socket()) {
            socket.connect(inetSocketAddress);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            // Send data to the server through the output stream
            // jdk serialize
            objectOutputStream.writeObject(rpcRequest);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            // Read RpcResponse from the input stream
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RpcException("调用服务失败:", e);
        }
    }
}
