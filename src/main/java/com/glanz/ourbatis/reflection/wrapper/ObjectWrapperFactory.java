package com.glanz.ourbatis.reflection.wrapper;

import com.glanz.ourbatis.reflection.MetaObject;

/**
 * @Author GlanzWen
 * @Description 对象包装工厂
 * @github
 */
public interface ObjectWrapperFactory {

    /**
     * 判断有没有包装器
     */
    boolean hasWrapperFor(Object object);

    /**
     * 得到包装器
     */
    ObjectWrapper getWrapperFor(MetaObject metaObject, Object object);

}
