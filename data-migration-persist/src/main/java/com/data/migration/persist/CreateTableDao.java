package com.data.migration.persist;

import java.sql.SQLException;

public interface CreateTableDao {

    String qryCreateTableSql(String type, String tableName) throws SQLException;

    boolean createTable(String type, String sql) throws SQLException;
}
