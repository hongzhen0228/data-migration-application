package com.data.migration.service.api;

import java.sql.SQLException;

/**
 * Created by hongzhen.cao on 2019/8/22.
 */
public interface ICreateTableService {
    String qryCreateTableSql(String type, String tableName) throws SQLException;

    boolean createTable(String type, String sql) throws SQLException;
}
