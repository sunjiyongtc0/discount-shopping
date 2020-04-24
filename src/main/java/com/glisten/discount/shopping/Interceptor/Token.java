package com.glisten.discount.shopping.Interceptor;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Token {

    /**
     * 是否创建新的token
     */
    boolean generate() default false;
    /**
     * 是否移除token
     */
    boolean remove() default false;
}
