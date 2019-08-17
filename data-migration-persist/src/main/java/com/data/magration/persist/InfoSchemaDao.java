package com.data.magration.persist;

import java.sql.SQLException;
import java.util.List;

public interface InfoSchemaDao {


    List<String> qryAllTableNames() throws SQLException;

}
