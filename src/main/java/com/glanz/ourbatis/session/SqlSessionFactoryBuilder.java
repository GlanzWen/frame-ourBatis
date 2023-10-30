package com.glanz.ourbatis.session;

import com.glanz.ourbatis.builder.xml.XMLConfigBuilder;
import com.glanz.ourbatis.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * @Author GlanzWen
 * @Description 构建SqlSessionFactory的工厂
 * @github
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }

}
