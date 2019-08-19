package com.data.migration.service;

import com.data.migration.dto.DataStructureDto;
import com.data.migration.dto.MysqlConnDto;
import com.data.migration.service.api.IDataMigrationService;
import com.data.migration.service.api.IDataStructureService;
import com.data.migration.service.api.IInfoSchemaService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStructureServiceTest extends BaseTest{

    @Autowired
    private IDataStructureService dataStructureService;
    @Autowired
    private IInfoSchemaService infoSchemaService;
    @Autowired
    private IDataMigrationService dataMigrationService;
    @Test
    public void test() throws SQLException, ClassNotFoundException {
        List<String> strings = infoSchemaService.qryAllTableNames(MysqlConnDto.TYPE_MASTER);
        //List<DataStructureDto> dataStructureDtoList = dataStructureService.qryDataStructure(strings.get(2), MysqlConnDto.TYPE_MASTER);
        List<Map<String, String>> mapList = dataMigrationService.qryByLimit(strings.get(21), MysqlConnDto.TYPE_MASTER, 0, 2000);
        System.out.println(mapList);
    }
}
