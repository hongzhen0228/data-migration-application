package com.data.migration.service.api;

import com.data.migration.dto.DataContainer;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by hongzhen.cao on 2019/8/19.
 */
public interface IDataMigrationService {

    List<DataContainer> qryByLimit(String tableName, String type, Integer start, Integer end) throws SQLException;

    boolean insert(String tableName, String type,List<DataContainer> dataContainerList) throws SQLException;

}
