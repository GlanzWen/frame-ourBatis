package com.glanz.ourbatis.scripting.xmltags;

import com.glanz.ourbatis.executor.parameter.ParameterHandler;
import com.glanz.ourbatis.mapping.BoundSql;
import com.glanz.ourbatis.mapping.MappedStatement;
import com.glanz.ourbatis.mapping.SqlSource;
import com.glanz.ourbatis.scripting.LanguageDriver;
import com.glanz.ourbatis.scripting.defaults.DefaultParameterHandler;
import com.glanz.ourbatis.scripting.defaults.RawSqlSource;
import com.glanz.ourbatis.session.Configuration;
import org.dom4j.Element;


/**
 * @Author GlanzWen
 * @Description XML语言驱动器
 * @github
 */
public class XMLLanguageDriver implements LanguageDriver {

    @Override
    public SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType) {
        // 用XML脚本构建器解析
        XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script, parameterType);
        return builder.parseScriptNode();
    }

    /**
     * step-12 新增方法，用于处理注解配置 SQL 语句
     */
    @Override
    public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {
        // 暂时不解析动态 SQL
        return new RawSqlSource(configuration, script, parameterType);
    }

    @Override
    public ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        return new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
    }

}
