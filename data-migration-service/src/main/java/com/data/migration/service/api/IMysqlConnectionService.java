package com.data.migration.service.api;

import com.data.migration.dto.MysqlConnDto;

import java.sql.SQLException;

public interface IMysqlConnectionService {

    boolean verifyConnection(MysqlConnDto mysqlConnDto) throws SQLException, ClassNotFoundException;
}
