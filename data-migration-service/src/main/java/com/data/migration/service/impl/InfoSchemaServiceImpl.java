package com.data.migration.service.impl;

import com.data.migration.persist.InfoSchemaDao;
import com.data.migration.service.api.IInfoSchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class InfoSchemaServiceImpl implements IInfoSchemaService {
    @Autowired
    private InfoSchemaDao infoSchemaDao;
    @Override
    public List<String> qryAllTableNames(String type) throws SQLException {
        return infoSchemaDao.qryAllTableNames(type);
    }

    @Override
    public boolean dropTable(String type, String tableName) throws SQLException {
        return infoSchemaDao.dropTable(type, tableName);
    }
}
