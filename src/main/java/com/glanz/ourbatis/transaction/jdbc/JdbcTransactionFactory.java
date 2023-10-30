package com.glanz.ourbatis.transaction.jdbc;

import com.glanz.ourbatis.session.TransactionIsolationLevel;
import com.glanz.ourbatis.transaction.Transaction;
import com.glanz.ourbatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;


/**
 * @Author GlanzWen
 * @Description JdbcTransaction 工厂
 * @github
 */
public class JdbcTransactionFactory implements TransactionFactory {

    @Override
    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(dataSource, level, autoCommit);
    }

}

