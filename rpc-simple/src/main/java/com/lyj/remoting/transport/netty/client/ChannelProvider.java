package com.lyj.remoting.transport.netty.client;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: My-Rpc
 * @description:
 * @author: lyj
 * @create: 2023-01-12 16:35
 **/
@Slf4j
public class ChannelProvider {

    private  final Map<String, Channel> channelMap;

    public ChannelProvider() {
        this.channelMap = new ConcurrentHashMap<>();
    }

    public Channel get(InetSocketAddress inetSocketAddress){
        String key = inetSocketAddress.toString();
        // determine if there is a connection for the corresponding address
        if (channelMap.containsKey(key)){
            Channel channel = channelMap.get(key);
            // if so , determine if the connection is available,and if so , get it directly
            if (channel!=null || channel.isActive()){
                return channel;
            }else {
                //if is null,remove it
                channelMap.remove(key);
            }
        }
        return null;
    }

    public void set(InetSocketAddress inetSocketAddress,Channel channel){
        String key = inetSocketAddress.toString();
        channelMap.put(key,channel);
    }

    public void remove(InetSocketAddress inetSocketAddress){
        String key = inetSocketAddress.toString();
        channelMap.remove(key);
        log.info("after removing , Channel map size :[{}]", channelMap.size());
    }
}
