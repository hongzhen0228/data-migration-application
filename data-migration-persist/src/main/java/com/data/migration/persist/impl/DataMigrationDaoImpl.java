package com.data.migration.persist.impl;

import com.data.migration.common.MysqlThreadHolder;
import com.data.migration.persist.DataMigrationDao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DataMigrationDaoImpl implements DataMigrationDao {


    @Override
    public List<Map<String,String>> qryByLimit(String tableName, String type, Integer start, Integer end) throws SQLException {
        Connection connection = MysqlThreadHolder.getConnection(type);
        Statement statement = connection.createStatement();
        String sql = "select * from " + tableName + " limit " + start + "," + end;
        List<Map<String,String>> mapList = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (resultSet.next()) {
            Map<String,String> paramMap = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                paramMap.put(metaData.getColumnLabel(i),resultSet.getString(i));
            }
            mapList.add(paramMap);
        }
        return mapList;
    }
}
