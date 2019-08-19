package com.data.migration.service;

import com.data.migration.dto.MysqlConnDto;
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
    @Test
    public void test() throws SQLException, ClassNotFoundException {
        List<String> strings = infoSchemaService.qryAllTableNames(MysqlConnDto.TYPE_MASTER);
        //List<DataStructureDto> dataStructureDtoList = dataStructureService.qryDataStructure(strings.get(2), MysqlConnDto.TYPE_MASTER);
        List<Map<String, String>> mapList = dataMigrationService.qryByLimit(strings.get(5), MysqlConnDto.TYPE_MASTER, 0, 2000);
        boolean b = infoSchemaService.dropTable(MysqlConnDto.TYPE_HOST, strings.get(5));
        if (b) {
            System.out.println("删除成功");
        }
        boolean insert = dataMigrationService.insert(strings.get(5), MysqlConnDto.TYPE_HOST, mapList);
        System.out.println(insert);
    }
}
