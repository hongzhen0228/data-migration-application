package com.data.migration.persist.impl;

import com.data.migration.common.MysqlThreadHolder;
import com.data.migration.persist.DataMigrationDao;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class DataMigrationDaoImpl implements DataMigrationDao {


    @Override
    public ResultSet qryByLimit(String tableName, String type, Integer start, Integer end) throws SQLException {
        Connection connection = MysqlThreadHolder.getConnection(type);
        Statement statement = connection.createStatement();
        String sql = "select * from " + tableName + " limit " + start + "," + end;
        return statement.executeQuery(sql);
    }
}
