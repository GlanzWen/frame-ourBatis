package com.glanz.ourbatis.executor.keygen;

import com.glanz.ourbatis.executor.Executor;
import com.glanz.ourbatis.mapping.MappedStatement;

import java.sql.Statement;

/**
 * @Author GlanzWen
 * @Description 不用键值生成器
 * @github
 */
public class NoKeyGenerator implements KeyGenerator{

    @Override
    public void processBefore(Executor executor, MappedStatement ms, Statement stmt, Object parameter) {
        // Do Nothing
    }

    @Override
    public void processAfter(Executor executor, MappedStatement ms, Statement stmt, Object parameter) {
        // Do Nothing
    }

}

