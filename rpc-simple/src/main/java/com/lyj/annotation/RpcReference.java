package com.lyj.annotation;

import java.lang.annotation.*;

/**
 * @program: My-Rpc
 * @description:  RPC reference annotation, autowire the service implementation class 消费服务
 * @author: lyj
 * @create: 2023-01-11 00:25
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Inherited
public @interface RpcReference {
    /**
     * Service version, default value is empty string
     */
    String version() default "";

    /**
     * Service group, default value is empty string
     */
    String group() default "";
}
