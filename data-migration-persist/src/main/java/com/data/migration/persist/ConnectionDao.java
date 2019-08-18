package com.data.migration.persist;

import com.data.migration.dto.MysqlConnDto;

import java.sql.SQLException;

public interface ConnectionDao {

    boolean connectionVerify(MysqlConnDto mysqlConnDto) throws SQLException, ClassNotFoundException;
}
