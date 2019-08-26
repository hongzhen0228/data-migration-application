package com.data.migration.common;

import com.data.migration.dto.DataContainer;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SqlBuilder {

    public static String buildInsertSql(DataContainer dataContainer, String tableName) {
        String sql = "INSERT INTO " + tableName + " (";
        List<DataContainer.MySqlData> mySqlDataList = dataContainer.getMySqlDataList();
        for (DataContainer.MySqlData mySqlData : mySqlDataList) {
            sql += mySqlData.getName() + ",";
        }
        sql = sql.substring(0,sql.length() -1) + ") VALUES (";
        return sql;
    }
}
