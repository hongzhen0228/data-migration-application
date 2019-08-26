package com.data.migration.service.impl;

import com.data.migration.persist.CreateTableDao;
import com.data.migration.service.api.ICreateTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by hongzhen.cao on 2019/8/22.
 */
@Service
public class CreateTableServiceImpl implements ICreateTableService{
    @Autowired
    private CreateTableDao createTableDao;
    @Override
    public String qryCreateTableSql(String type, String tableName) throws SQLException {
        return createTableDao.qryCreateTableSql(type, tableName);
    }

    @Override
    public boolean createTable(String type, String sql) throws SQLException {
        return createTableDao.createTable(type, sql);
    }
}
