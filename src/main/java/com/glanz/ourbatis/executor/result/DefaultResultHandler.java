package com.glanz.ourbatis.executor.result;

import com.glanz.ourbatis.reflection.factory.ObjectFactory;
import com.glanz.ourbatis.session.ResultContext;
import com.glanz.ourbatis.session.ResultHandler;

import java.util.ArrayList;
import java.util.List;
/**
 * @Author GlanzWen
 * @Description 默认结果处理器
 * @github
 */
public class DefaultResultHandler implements ResultHandler {

    private final List<Object> list;

    public DefaultResultHandler() {
        this.list = new ArrayList<>();
    }

    /**
     * 通过 ObjectFactory 反射工具类，产生特定的 List
     */
    @SuppressWarnings("unchecked")
    public DefaultResultHandler(ObjectFactory objectFactory) {
        this.list = objectFactory.create(List.class);
    }

    @Override
    public void handleResult(ResultContext context) {
        list.add(context.getResultObject());
    }

    public List<Object> getResultList() {
        return list;
    }

}

