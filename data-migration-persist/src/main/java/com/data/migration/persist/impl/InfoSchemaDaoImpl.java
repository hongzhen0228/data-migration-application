package com.data.migration.persist.impl;

import com.data.migration.common.MysqlThreadHolder;
import com.data.migration.persist.InfoSchemaDao;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InfoSchemaDaoImpl implements InfoSchemaDao {

    @Override
    public List<String> qryAllTableNames(String type) throws SQLException {
        List<String> tableNames = new ArrayList<>();
        Connection connection = MysqlThreadHolder.getConnection(type);
        String schema = MysqlThreadHolder.getSchema(type);
        String sql = "select table_name from information_schema.tables where table_schema=" + "\'" + schema + "\'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            tableNames.add(resultSet.getString("table_name"));
        }
        return tableNames;
    }

    @Override
    public boolean dropTable(String type, String tableName) throws SQLException {
        Connection connection = MysqlThreadHolder.getConnection(type);
        String sql = "DROP TABLE IF EXISTS " + tableName;
        Statement statement = connection.createStatement();
        return statement.execute(sql);
    }
}
