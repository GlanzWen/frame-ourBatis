package com.glanz.ourbatis.session;

/**
 * @Author GlanzWen
 * @Description 结果上下文
 * @github
 */
public interface ResultContext {

    /**
     * 获取结果
     */
    Object getResultObject();

    /**
     * 获取记录数
     */
    int getResultCount();

}

