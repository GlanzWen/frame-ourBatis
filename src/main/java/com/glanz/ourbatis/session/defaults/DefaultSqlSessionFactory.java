package com.glanz.ourbatis.session.defaults;

import com.glanz.ourbatis.executor.Executor;
import com.glanz.ourbatis.mapping.Environment;
import com.glanz.ourbatis.session.Configuration;
import com.glanz.ourbatis.session.SqlSession;
import com.glanz.ourbatis.session.SqlSessionFactory;
import com.glanz.ourbatis.session.TransactionIsolationLevel;
import com.glanz.ourbatis.transaction.Transaction;
import com.glanz.ourbatis.transaction.TransactionFactory;


import java.sql.SQLException;

/**
 * @Author GlanzWen
 * @Description 默认的 DefaultSqlSessionFactory
 * @github
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        Transaction tx = null;
        try {
            final Environment environment = configuration.getEnvironment();
            TransactionFactory transactionFactory = environment.getTransactionFactory();
            tx = transactionFactory.newTransaction(configuration.getEnvironment().getDataSource(), TransactionIsolationLevel.READ_COMMITTED, false);
            // 创建执行器
            final Executor executor = configuration.newExecutor(tx);
            // 创建DefaultSqlSession
            return new DefaultSqlSession(configuration, executor);
        } catch (Exception e) {
            try {
                assert tx != null;
                tx.close();
            } catch (SQLException ignore) {
            }
            throw new RuntimeException("Error opening session.  Cause: " + e);
        }
    }

}

