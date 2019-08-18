package com.data.migration.service;

import com.data.migration.dto.MysqlConnDto;
import com.data.migration.service.api.IMysqlConnectionService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
            System.out.println("mysql连接成功");
        }

    }

}