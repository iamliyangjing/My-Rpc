package com.lyj.annotation;

import java.lang.annotation.*;

/**
 * @program: My-Rpc
 * @description: RPC service annotation, marked on the service implementation class
 * @author: lyj
 * @create: 2023-01-11 00:53
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface RpcService {

    /**
     * Service version, default value is empty string
     */
    String version() default "";

    /**
     * Service group, default value is empty string
     */
    String group() default "";

}
