package com.glanz.ourbatis.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @Author GlanzWen
 * @Description Long类型处理器
 * @github
 */
public class LongTypeHandler extends BaseTypeHandler<Long> {

    @Override
    protected void setNonNullParameter(PreparedStatement ps, int i, Long parameter, JdbcType jdbcType) throws SQLException {
        ps.setLong(i, parameter);
    }

    @Override
    protected Long getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getLong(columnName);
    }

    @Override
    public Long getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getLong(columnIndex);
    }

}
