package com.lyj.loadbalance;

import com.lyj.remoting.dto.RpcRequest;
import com.lyj.utils.CollectionUtil;

import java.util.List;

/**
 * @program: My-Rpc
 * @description:
 * @author: lyj
 * @create: 2023-01-12 10:36
 **/
public abstract class AbstractLoadBalancer implements LoadBalance {

    @Override
    public String selectServiceAddress(List<String> serviceAddresses, RpcRequest rpcRequest) {
        if (CollectionUtil.isEmpty(serviceAddresses)){
            return null;
        }
        if (serviceAddresses.size() == 1){
            return serviceAddresses.get(0);
        }
        return doSelect(serviceAddresses,rpcRequest);
    }

    protected abstract String doSelect(List<String> serviceAddress, RpcRequest rpcRequest);
}
