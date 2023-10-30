package com.glanz.ourbatis.scripting.defaults;

import com.glanz.ourbatis.builder.SqlSourceBuilder;
import com.glanz.ourbatis.mapping.BoundSql;
import com.glanz.ourbatis.mapping.SqlSource;
import com.glanz.ourbatis.scripting.xmltags.DynamicContext;
import com.glanz.ourbatis.scripting.xmltags.SqlNode;
import com.glanz.ourbatis.session.Configuration;

import java.util.HashMap;


/**
 * @Author GlanzWen
 * @Description 原始SQL源码，比 DynamicSqlSource 动态SQL处理快
 * @github
 */
public class RawSqlSource implements SqlSource {

    private final SqlSource sqlSource;

    public RawSqlSource(Configuration configuration, SqlNode rootSqlNode, Class<?> parameterType) {
        this(configuration, getSql(configuration, rootSqlNode), parameterType);
    }

    public RawSqlSource(Configuration configuration, String sql, Class<?> parameterType) {
        SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
        Class<?> clazz = parameterType == null ? Object.class : parameterType;
        sqlSource = sqlSourceParser.parse(sql, clazz, new HashMap<>());
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return sqlSource.getBoundSql(parameterObject);
    }

    private static String getSql(Configuration configuration, SqlNode rootSqlNode) {
        DynamicContext context = new DynamicContext(configuration, null);
        rootSqlNode.apply(context);
        return context.getSql();
    }

}
