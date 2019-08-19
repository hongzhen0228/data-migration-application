package com.data.migration.persist;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DataMigrationDao {

    List<Map<String,String>> qryByLimit(String tableName, String type, Integer start, Integer end) throws SQLException;

    boolean insert(String tableName, String type, List<Map<String,String>> mapList) throws SQLException;
}
