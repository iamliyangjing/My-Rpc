package com.lyj.loadbalance.loadbalancer;

import com.lyj.loadbalance.AbstractLoadBalancer;
import com.lyj.remoting.dto.RpcRequest;
import org.jboss.netty.util.internal.ThreadLocalRandom;

import java.util.List;
import java.util.Random;

/**
 * @program: My-Rpc
 * @description:  Implementation of random load balancing strategy
 * @author: lyj
 * @create: 2023-01-12 10:39
 **/
public class RandomLoadBalance extends AbstractLoadBalancer {
    @Override
    protected String doSelect(List<String> serviceAddress, RpcRequest rpcRequest) {
        /**
         * ensure thread safety
         */
        Random random = new ThreadLocalRandom();
        return serviceAddress.get(random.nextInt(serviceAddress.size()));
    }
}
