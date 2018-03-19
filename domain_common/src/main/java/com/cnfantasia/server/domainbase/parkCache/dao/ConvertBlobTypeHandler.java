package com.cnfantasia.server.domainbase.parkCache.dao;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class ConvertBlobTypeHandler extends BaseTypeHandler<byte[]> {

    @Override
    public byte[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Blob blob = rs.getBlob(columnName);
        return blob.getBytes(1, (int) blob.length());
    }

    @Override
    public byte[] getNullableResult(ResultSet rs, int columnName) throws SQLException {
        Blob blob = rs.getBlob(columnName);
        return blob.getBytes(1, (int) blob.length());
    }

    @Override
    public void setNonNullParameter(java.sql.PreparedStatement ps, int i, byte[] parameter, JdbcType jdbcType) throws SQLException {
        ps.setBytes(i, parameter);
    }

    @Override
    public byte[] getNullableResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
        Blob blob = cs.getBlob(columnIndex);
        return blob.getBytes(1, (int) blob.length());
    }
}
