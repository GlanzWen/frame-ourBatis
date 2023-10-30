package com.glanz.ourbatis.mapping;

/**
 * @Author GlanzWen
 * @Description SQL源码
 * @github
 */
public interface SqlSource {

    BoundSql getBoundSql(Object parameterObject);

}
