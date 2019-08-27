package com.data.migration.dto;

import lombok.Data;

import java.sql.Connection;

@Data
public class MysqlConnDto {

    public final static String TYPE_MASTER = "master";

    public final static String TYPE_HOST = "host";

    private String username;

    private String password;

    private String url;

    private String port;

    private String schema;

    private Connection connection;

    private String type;

    public MysqlConnDto(String url, String username,String password, String schema, String type){
        this.url = url;
        this.username = username;
        this.password = password;
        this.schema = schema;
        this.type = type;
    }

    public MysqlConnDto() {
    }
}
