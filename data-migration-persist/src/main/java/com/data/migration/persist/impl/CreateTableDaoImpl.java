package com.data.migration.persist.impl;

import com.data.migration.common.MysqlThreadHolder;
import com.data.migration.persist.CreateTableDao;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class CreateTableDaoImpl implements CreateTableDao {
    @Override
    public String qryCreateTableSql(String type, String tableName) throws SQLException {
        Connection connection = MysqlThreadHolder.getConnection(type);
        Statement statement = connection.createStatement();
        String sql ="show create table " + tableName;
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            return resultSet.getString("create table");
        }
        return null;
    }

    @Override
    public boolean createTable(String type, String sql) throws SQLException {
        try {
            Connection connection = MysqlThreadHolder.getConnection(type);
            Statement statement = connection.createStatement();
            int i = statement.executeUpdate(sql);
            if (i == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
