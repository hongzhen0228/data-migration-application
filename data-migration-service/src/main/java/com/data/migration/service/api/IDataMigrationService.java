package com.data.migration.service.api;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by hongzhen.cao on 2019/8/19.
 */
public interface IDataMigrationService {

    List<Map<String,String>> qryByLimit(String tableName, String type, Integer start, Integer end) throws SQLException;

}
