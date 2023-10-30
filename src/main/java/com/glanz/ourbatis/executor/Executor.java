package com.glanz.ourbatis.executor;

import com.glanz.ourbatis.cache.CacheKey;
import com.glanz.ourbatis.mapping.BoundSql;
import com.glanz.ourbatis.mapping.MappedStatement;
import com.glanz.ourbatis.session.ResultHandler;
import com.glanz.ourbatis.session.RowBounds;
import com.glanz.ourbatis.transaction.Transaction;
import com.glanz.ourbatis.mapping.MappedStatement;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author GlanzWen
 * @Description 执行器
 * @github
 */
public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    int update(MappedStatement ms, Object parameter) throws SQLException;

    // 查询，含缓存
    <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, CacheKey key, BoundSql boundSql) throws SQLException;

    // 查询
    <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) throws SQLException;

    Transaction getTransaction();

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback);

    // 清理Session缓存
    void clearLocalCache();

    // 创建缓存 Key
    CacheKey createCacheKey(MappedStatement ms, Object parameterObject, RowBounds rowBounds, BoundSql boundSql);

    void setExecutorWrapper(Executor executor);

}
