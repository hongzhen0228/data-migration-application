package com.data.migration.persist;

import java.sql.SQLException;
import java.util.List;

public interface InfoSchemaDao {


    List<String> qryAllTableNames(String type) throws SQLException;

    boolean dropTable(String type, String tableName) throws SQLException;

}
