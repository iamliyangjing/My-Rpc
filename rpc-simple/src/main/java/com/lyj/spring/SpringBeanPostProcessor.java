package com.lyj.spring;

import com.lyj.annotation.RpcReference;
import com.lyj.annotation.RpcService;
import com.lyj.config.RpcServiceConfig;
import com.lyj.extension.ExtensionLoader;
import com.lyj.factory.SingletonFactory;
import com.lyj.provider.ServiceProvider;
import com.lyj.provider.impl.ZkServiceProviderImpl;
import com.lyj.proxy.RpcClientProxy;
import com.lyj.remoting.transport.RpcRequestTransport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @program: My-Rpc
 * @description:
 * @author: lyj
 * @create: 2023-01-11 00:54
 **/
@Slf4j
@Component
public class SpringBeanPostProcessor implements BeanPostProcessor {

    private final ServiceProvider serviceProvider;

    private final RpcRequestTransport rpcClient;

    public SpringBeanPostProcessor() {
        this.serviceProvider = SingletonFactory.getInstance(ZkServiceProviderImpl.class);
        this.rpcClient = ExtensionLoader.getExtensionLoader(RpcRequestTransport.class).getExtension("netty");
    }

    /**
     *  @SneakyThrows 帮助我们减少异常类代码
     *     @SneakyThrows
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(RpcService.class)){
            log.info("[{}] is annotated with [{}]",bean.getClass().getName(),RpcService.class.getCanonicalName());
            // get RpcService annotation
            RpcService rpcService = bean.getClass().getAnnotation(RpcService.class);
            // build RpcServiceProperties
            RpcServiceConfig rpcServiceConfig = RpcServiceConfig.builder()
                    .group(rpcService.group())
                    .version(rpcService.version())
                    .service(bean).build();
            serviceProvider.publishService(rpcServiceConfig);
        }
        return bean;
    }

    /**
     * 消费服务
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = bean.getClass();
        Field[] declaredFields = targetClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            RpcReference rpcReference = declaredField.getAnnotation(RpcReference.class);
            if (null != rpcReference){
                RpcServiceConfig rpcServiceConfig = RpcServiceConfig.builder()
                        .group(rpcReference.group())
                        .version(rpcReference.version())
                        .build();
                RpcClientProxy clientProxy = new RpcClientProxy(rpcClient, rpcServiceConfig);
                Object proxy = clientProxy.getProxy(declaredField.getType());
                declaredField.setAccessible(true);
                try {
                    declaredField.set(bean,proxy);
                }catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }
}
