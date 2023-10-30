package com.glanz.ourbatis.reflection.wrapper;

import com.glanz.ourbatis.reflection.MetaObject;

/**
 * @Author GlanzWen
 * @Description 默认对象包装工厂
 * @github
 */
public class DefaultObjectWrapperFactory implements ObjectWrapperFactory{

    @Override
    public boolean hasWrapperFor(Object object) {
        return false;
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
        throw new RuntimeException("The DefaultObjectWrapperFactory should never be called to provide an ObjectWrapper.");
    }

}

