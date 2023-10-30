package com.glanz.ourbatis.builder;

import com.glanz.ourbatis.mapping.BoundSql;
import com.glanz.ourbatis.mapping.ParameterMapping;
import com.glanz.ourbatis.mapping.SqlSource;
import com.glanz.ourbatis.session.Configuration;

import java.util.List;

/**
 * @Author GlanzWen
 * @Description 静态SQL源码
 * @github:
 */
public class StaticSqlSource implements SqlSource {

    private String sql;
    private List<ParameterMapping> parameterMappings;
    private Configuration configuration;

    public StaticSqlSource(Configuration configuration, String sql) {
        this(configuration, sql, null);
    }

    public StaticSqlSource(Configuration configuration, String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.configuration = configuration;
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return new BoundSql(configuration, sql, parameterMappings, parameterObject);
    }

}
