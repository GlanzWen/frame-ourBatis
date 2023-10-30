package com.glanz.ourbatis.datasource.pooled;

import com.glanz.ourbatis.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * @Author GlanzWen
 * @Description 有连接池的数据源工厂
 * @github
 */
public class PooledDataSourceFactory extends UnpooledDataSourceFactory {

    public PooledDataSourceFactory() {
        this.dataSource = new PooledDataSource();
    }

}
