package com.data.migration.persist.impl;

import com.data.migration.common.MysqlThreadHolder;
import com.data.migration.common.SqlBuilder;
import com.data.migration.persist.DataMigrationDao;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.sql.*;
import java.util.*;

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
                int columnType = metaData.getColumnType(i);
                if (columnType == Types.INTEGER || columnType == Types.BIGINT || columnType == Types.FLOAT) {
                    paramMap.put(metaData.getColumnLabel(i),resultSet.getString(i));
                } else {
                    paramMap.put(metaData.getColumnLabel(i),"'" + resultSet.getString(i) +"'");
                }

            }
            mapList.add(paramMap);
        }
        return mapList;
    }

    @Override
    public boolean insert(String tableName, String type, List<Map<String, String>> mapList) throws SQLException {
        Connection connection = MysqlThreadHolder.getConnection(type);
        if (!CollectionUtils.isEmpty(mapList)) {
            Map<String, String> keyMap = mapList.get(0);
            String sql = SqlBuilder.buildInsertSql(keyMap, tableName);
            for (Map<String, String> dataMap : mapList) {
                Set<String> keys = dataMap.keySet();
                for (String key : keys) {
                    String value = dataMap.get(key);
                    sql += value +",";
                }
                sql = sql.substring(0,sql.length() -1) + "),(";
            }
            sql = sql.substring(0,sql.length() - 2);
            Statement statement = connection.createStatement();
            return statement.execute(sql);
        }
        return false;
    }
}
