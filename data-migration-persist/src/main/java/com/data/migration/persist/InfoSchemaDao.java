package com.data.migration.persist;

import java.sql.SQLException;
import java.util.List;

public interface InfoSchemaDao {


    List<String> qryAllTableNames(String type) throws SQLException;

}
