package com.glanz.ourbatis.executor.statement;

import com.glanz.ourbatis.mapping.BoundSql;
import com.glanz.ourbatis.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @Author GlanzWen
 * @Description 语句处理器
 * @github
 */
public interface StatementHandler {

    /** 准备语句 */
    Statement prepare(Connection connection) throws SQLException;

    /** 参数化 */
    void parameterize(Statement statement) throws SQLException;

    /** 执行更新 */
    int update(Statement statement) throws SQLException;

    /** 执行查询 */
    <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException;

    /** 获取绑定SQL */
    BoundSql getBoundSql();

}

