package com.lyj.annotation;

import com.lyj.spring.CustomScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @program: My-Rpc
 * @description: scan custom annotations
 * @author: lyj
 * @create: 2023-01-11 00:25
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Import(CustomScannerRegistrar.class)
@Documented
public @interface RpcScan {
    String[] basePackage();

}
