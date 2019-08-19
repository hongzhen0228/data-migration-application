package com.data.migration.persist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DataMigrationDao {

    List<Map<String,String>> qryByLimit(String tableName, String type, Integer start, Integer end) throws SQLException;
}
