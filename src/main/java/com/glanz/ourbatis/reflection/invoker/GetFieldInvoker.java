package com.glanz.ourbatis.reflection.invoker;

import java.lang.reflect.Field;

/**
 * @Author GlanzWen
 * @Description getter 调用者
 * @github
 */
public class GetFieldInvoker implements Invoker {

    private Field field;

    public GetFieldInvoker(Field field) {
        this.field = field;
    }

    @Override
    public Object invoke(Object target, Object[] args) throws Exception {
        return field.get(target);
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }

}

