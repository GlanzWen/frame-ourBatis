package com.glanz.ourbatis.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author GlanzWen
 * @Description 事务接口
 * @github
 */
public interface Transaction {

    Connection getConnection() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    void close() throws SQLException;

}
