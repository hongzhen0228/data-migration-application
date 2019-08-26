package com.data.migration.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnector {

    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";

    private final static String URL_PREFIX = "jdbc:mysql://";

    private final static String URL_SUFFIX = "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&autoReconnect=true&failOverReadOnly=false&maxReconnects=1000&initialTimeout=30";

    public static Connection getConnection(String url, String username, String password) throws ClassNotFoundException {
        Connection connection = null;
        Class.forName(DRIVER);
        try {
            connection = DriverManager.getConnection(URL_PREFIX + url + URL_SUFFIX,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
