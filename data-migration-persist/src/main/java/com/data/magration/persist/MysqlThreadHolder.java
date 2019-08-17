package com.data.magration.persist;

import com.data.magration.dto.MysqlThreadData;

import java.sql.Connection;
import java.sql.SQLException;


public class MysqlThreadHolder {

    private static final ThreadLocal<MysqlThreadData> threadLocal = new ThreadLocal();

    public static void setInfo(MysqlThreadData mysqlThreadData) {
        threadLocal.set(mysqlThreadData);
    }


    public static MysqlThreadData getInfo(){
        return threadLocal.get();
    }

    public static void remove(){
        threadLocal.remove();
        Connection connection = threadLocal.get().getConnection();
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
