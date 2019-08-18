package com.data.migration.common;

import com.data.migration.dto.MysqlConnDto;
import com.data.migration.dto.MysqlThreadData;

import java.sql.Connection;
import java.sql.SQLException;


public class MysqlThreadHolder {

    private static final ThreadLocal<MysqlThreadData> threadLocal = new ThreadLocal();

    public static void setInfo(MysqlThreadData mysqlThreadData) {
        threadLocal.set(mysqlThreadData);
    }


    public static MysqlConnDto getInfo(String type){
        if (type.equals(MysqlConnDto.TYPE_MASTER)) {
            return threadLocal.get().getMasterConnDto();
        }
        return threadLocal.get().getHostConnDto();
    }

    public static Connection getConnection(String type) {
        return getInfo(type).getConnection();
    }

    public static String getSchema(String type) {
        return getInfo(type).getSchema();
    }

    public static void remove(){
        threadLocal.remove();
        Connection hostConnection = threadLocal.get().getHostConnDto().getConnection();
        Connection masterConnection = threadLocal.get().getMasterConnDto().getConnection();
        if (hostConnection != null) {
            try {
                hostConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (masterConnection != null) {
            try {
                masterConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
