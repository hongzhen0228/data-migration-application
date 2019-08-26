package com.data.migration.service;

import com.data.migration.dto.DataContainer;
import com.data.migration.dto.MysqlConnDto;
import com.data.migration.service.api.ICreateTableService;
import com.data.migration.service.api.IDataMigrationService;
import com.data.migration.service.api.IDataStructureService;
import com.data.migration.service.api.IInfoSchemaService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DataStructureServiceTest extends BaseTest{

    @Autowired
    private IDataStructureService dataStructureService;
    @Autowired
    private IInfoSchemaService infoSchemaService;
    @Autowired
    private IDataMigrationService dataMigrationService;
    @Autowired
    private ICreateTableService createTableService;
    @Test
    public void test() throws SQLException, ClassNotFoundException {
        List<String> strings = infoSchemaService.qryAllTableNames(MysqlConnDto.TYPE_MASTER);
        //List<DataStructureDto> dataStructureDtoList = dataStructureService.qryDataStructure(strings.get(2), MysqlConnDto.TYPE_MASTER);
        long start = System.currentTimeMillis();
        List<DataContainer> dataContainerList = dataMigrationService.qryByLimit("unknown_host", MysqlConnDto.TYPE_MASTER, 0, 2000);
        boolean b = infoSchemaService.dropTable(MysqlConnDto.TYPE_HOST, "unknown_host");
        if (b) {
            System.out.println("删除成功");
        }
        String tableSql = createTableService.qryCreateTableSql(MysqlConnDto.TYPE_MASTER, "unknown_host");
        createTableService.createTable(MysqlConnDto.TYPE_HOST, tableSql);
        boolean insert = dataMigrationService.insert("unknown_host", MysqlConnDto.TYPE_HOST, dataContainerList);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }



}
