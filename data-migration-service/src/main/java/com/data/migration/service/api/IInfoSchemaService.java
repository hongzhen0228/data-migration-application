package com.data.migration.service.api;

import java.sql.SQLException;
import java.util.List;

public interface IInfoSchemaService {

    List<String> qryAllTableNames(String type) throws SQLException;

    boolean dropTable(String type, String tableName) throws SQLException;

}
