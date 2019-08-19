package com.data.migration.service.impl;

import com.data.migration.persist.DataMigrationDao;
import com.data.migration.service.api.IDataMigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by hongzhen.cao on 2019/8/19.
 */
@Service
public class DataMigrationServiceImpl implements IDataMigrationService{
    @Autowired
    private DataMigrationDao dataMigrationDao;
    @Override
    public List<Map<String, String>> qryByLimit(String tableName, String type, Integer start, Integer end) throws SQLException {
        return dataMigrationDao.qryByLimit(tableName, type, start, end);
    }
}
