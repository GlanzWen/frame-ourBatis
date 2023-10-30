package com.glanz.ourbatis.session;

/**
 * @Author GlanzWen
 * @Description 工厂模式接口，构建SqlSession的工厂
 * @github
 */
public interface SqlSessionFactory {

    /**
     * 打开一个 session
     * @return SqlSession
     */
    SqlSession openSession();

}
