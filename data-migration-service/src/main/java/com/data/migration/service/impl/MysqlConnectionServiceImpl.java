package com.data.migration.service.impl;

import com.data.migration.dto.MysqlConnDto;
import com.data.migration.persist.ConnectionDao;
import com.data.migration.service.api.IMysqlConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class MysqlConnectionServiceImpl implements IMysqlConnectionService {

    @Autowired
    private ConnectionDao connectionDao;

    @Override
    public boolean verifyConnection(MysqlConnDto mysqlConnDto) throws SQLException, ClassNotFoundException {
        return connectionDao.connectionVerify(mysqlConnDto);
    }
}
