package com.glanz.ourbatis.scripting;

import com.glanz.ourbatis.executor.parameter.ParameterHandler;
import com.glanz.ourbatis.mapping.BoundSql;
import com.glanz.ourbatis.mapping.MappedStatement;
import com.glanz.ourbatis.mapping.SqlSource;
import com.glanz.ourbatis.session.Configuration;
import org.dom4j.Element;

/**
 * @Author GlanzWen
 * @Description 脚本语言驱动
 * @github
 */
public interface LanguageDriver {

    /**
     * 创建SQL源码(mapper xml方式)
     */
    SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType);

    /**
     * 创建SQL源码(annotation 注解方式)
     */
    SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType);

    /**
     * 创建参数处理器
     */
    ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql);

}
