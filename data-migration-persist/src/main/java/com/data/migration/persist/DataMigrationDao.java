package com.data.migration.persist;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataMigrationDao {

    ResultSet qryByLimit(String tableName, String type, Integer start, Integer end) throws SQLException;
}
