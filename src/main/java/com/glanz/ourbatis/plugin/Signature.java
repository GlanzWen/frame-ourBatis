package com.glanz.ourbatis.plugin;

/**
 * @Author GlanzWen
 * @Description 方法签名
 * @github
 */
public @interface Signature {

    /**
     * 被拦截类
     */
    Class<?> type();

    /**
     * 被拦截类的方法
     */
    String method();

    /**
     * 被拦截类的方法的参数
     */
    Class<?>[] args();

}
