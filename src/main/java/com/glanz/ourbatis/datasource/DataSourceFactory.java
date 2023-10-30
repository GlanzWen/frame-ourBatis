package com.glanz.ourbatis.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @Author GlanzWen
 * @Description 数据源工厂
 * @github
 */
public interface DataSourceFactory {

    void setProperties(Properties props);

    DataSource getDataSource();

}
