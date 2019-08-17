package com.data.magration.dto;

import java.sql.Connection;

public class MysqlThreadData {

    private Connection connection;

    private String schema;

    private String url;

    private String username;

    private String password;

    public Connection getConnection() {
        return connection;
    }

    public MysqlThreadData setConnection(Connection connection) {
        this.connection = connection;
        return this;
    }

    public String getSchema() {
        return schema;
    }

    public MysqlThreadData setSchema(String schema) {
        this.schema = schema;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public MysqlThreadData setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public MysqlThreadData setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public MysqlThreadData setPassword(String password) {
        this.password = password;
        return this;
    }
}
