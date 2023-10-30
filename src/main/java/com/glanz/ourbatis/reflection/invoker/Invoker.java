package com.glanz.ourbatis.reflection.invoker;

/**
 * @Author GlanzWen
 * @Description 调用者
 * @github
 */
public interface Invoker {

    Object invoke(Object target, Object[] args) throws Exception;

    Class<?> getType();

}

