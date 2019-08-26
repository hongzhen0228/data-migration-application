package com.data.migration.persist;

import com.data.migration.dto.DataContainer;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DataMigrationDao {

    List<DataContainer> qryByLimit(String tableName, String type, Integer start, Integer end) throws SQLException;

    boolean insert(String tableName, String type, List<DataContainer> dataContainerList) throws SQLException;
}
