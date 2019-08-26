package com.data.migration.persist.impl;

import com.data.migration.common.MysqlThreadHolder;
import com.data.migration.common.SqlBuilder;
import com.data.migration.dto.DataContainer;
import com.data.migration.persist.DataMigrationDao;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.sql.*;
import java.util.*;

@Repository
public class DataMigrationDaoImpl implements DataMigrationDao {


    @Override
    public List<DataContainer> qryByLimit(String tableName, String type, Integer start, Integer end) throws SQLException {
        Connection connection = MysqlThreadHolder.getConnection(type);
        Statement statement = connection.createStatement();
        String sql = "select * from " + tableName + " limit " + start + "," + end;
        List<DataContainer> dataContainerList = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (resultSet.next()) {
            DataContainer dataContainer = new DataContainer();
            for (int i = 1; i <= columnCount; i++) {
                DataContainer.MySqlData mySqlData = new DataContainer.MySqlData(metaData.getColumnLabel(i),resultSet.getString(i),metaData.getColumnType(i));
                dataContainer.putValue(mySqlData);
            }
            dataContainerList.add(dataContainer);
        }
        return dataContainerList;
    }

    @Override
    public boolean insert(String tableName, String type, List<DataContainer> dataContainerList) throws SQLException {
        Connection connection = MysqlThreadHolder.getConnection(type);
        if (!CollectionUtils.isEmpty(dataContainerList)) {
            DataContainer dataContainer = dataContainerList.get(0);
            String sql = SqlBuilder.buildInsertSql(dataContainer, tableName);
            for (DataContainer container : dataContainerList) {
                List<DataContainer.MySqlData> mySqlDataList = container.getMySqlDataList();
                for (DataContainer.MySqlData mySqlData : mySqlDataList) {
                    sql += mySqlData.getValue() + ",";
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
