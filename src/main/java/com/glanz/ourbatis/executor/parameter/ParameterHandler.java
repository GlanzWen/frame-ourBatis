package com.glanz.ourbatis.executor.parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author GlanzWen
 * @Description 参数处理器
 * @github
 */
public interface ParameterHandler {

    /**
     * 获取参数
     */
    Object getParameterObject();

    /**
     * 设置参数
     */
    void setParameters(PreparedStatement ps) throws SQLException;

}

