package com.data.migration.common;

import com.data.migration.dto.MysqlThreadData;

public class SingletonUtils {

    private static MysqlThreadData mysqlThreadData = new MysqlThreadData();

    private SingletonUtils(){}

    public static MysqlThreadData getMysqlThreadData(){
        return mysqlThreadData;
    }
}
