package com.data.migration.common;

import java.util.Map;
import java.util.Set;

public class SqlBuilder {

    public static String buildInsertSql(Map<String,String> map,String tableName) {
        String sql = "INSERT INTO " + tableName + " (";
        Set<String> keys = map.keySet();
        for (String key : keys) {
            sql += key + ",";
        }
        sql = sql.substring(0,sql.length() -1) + ") VALUES (";
        return sql;
    }
}
