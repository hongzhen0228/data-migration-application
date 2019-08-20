package com.data.migration.service;

import com.data.migration.common.MysqlThreadHolder;
import com.data.migration.dto.MysqlConnDto;
import com.data.migration.service.api.IMysqlConnectionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataMigrationServiceApplication.class)
public class BaseTest {





    @Autowired
    private IMysqlConnectionService connectionService;

    @Before
    public void createConn() throws SQLException, ClassNotFoundException {
        MysqlConnDto mysqlConnDto = new MysqlConnDto();
        mysqlConnDto.setUrl("rm-wz986i60p423gcku3qo.mysql.rds.aliyuncs.com");
        mysqlConnDto.setPassword("Cao8231669@");
        mysqlConnDto.setUsername("root");
        mysqlConnDto.setSchema("db_mblog");
        mysqlConnDto.setPort("3306");
        mysqlConnDto.setType(MysqlConnDto.TYPE_MASTER);
        boolean b = connectionService.verifyConnection(mysqlConnDto);
        if (b) {
            System.out.println("******************主mysql连接成功");
        }
        MysqlConnDto mysqlConnDto1 = new MysqlConnDto();
        mysqlConnDto1.setUrl("rm-wz986i60p423gcku3qo.mysql.rds.aliyuncs.com");
        mysqlConnDto1.setPassword("Cao8231669@");
        mysqlConnDto1.setUsername("root");
        mysqlConnDto1.setSchema("cust");
        mysqlConnDto1.setPort("3306");
        mysqlConnDto1.setType(MysqlConnDto.TYPE_HOST);
        boolean c = connectionService.verifyConnection(mysqlConnDto1);
        if (c) {
            System.out.println("******************从mysql连接成功");
        }
        Connection connection = MysqlThreadHolder.getConnection(MysqlConnDto.TYPE_HOST);
        Connection connection2 = MysqlThreadHolder.getConnection(MysqlConnDto.TYPE_MASTER);

        System.out.println(connection);
    }
}
