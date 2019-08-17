package com.data.magration.persist.impl;

import com.data.magration.dto.MysqlThreadData;
import com.data.magration.persist.InfoSchemaDao;
import com.data.magration.persist.MysqlThreadHolder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InfoSchemaDaoImpl implements InfoSchemaDao {

    @Override
    public List<String> qryAllTableNames() throws SQLException {
        System.out.println(Thread.currentThread().getName());
        List<String> tableNames = new ArrayList<>();
        MysqlThreadData mysqlThreadData = MysqlThreadHolder.getInfo();
        Connection connection = mysqlThreadData.getConnection();
        String schema = mysqlThreadData.getSchema();
        String sql = "select table_name from information_schema.tables where table_schema=" + "\'" + schema + "\'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            tableNames.add(resultSet.getString("table_name"));
        }
        return tableNames;
    }
}
