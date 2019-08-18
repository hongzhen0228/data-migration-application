package com.data.migration.service;

import com.data.migration.dto.DataStructureDto;
import com.data.migration.dto.MysqlConnDto;
import com.data.migration.service.api.IDataStructureService;
import com.data.migration.service.api.IInfoSchemaService;
import com.data.migration.service.common.DynamicBeanGenerator;
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
    @Test
    public void test() throws SQLException, ClassNotFoundException {
        List<String> strings = infoSchemaService.qryAllTableNames(MysqlConnDto.TYPE_MASTER);
        List<DataStructureDto> dataStructureDtoList = dataStructureService.qryDataStructure(strings.get(1), MysqlConnDto.TYPE_MASTER);
        Map map = DynamicBeanGenerator.generteTableMap(dataStructureDtoList);
        DynamicBeanGenerator dynamicBeanGenerator = new DynamicBeanGenerator();
        Object o = dynamicBeanGenerator.generateBean(map);
        System.out.println(o);
    }

    /*CREATE TABLE `unknown_host` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip_address` varchar(255) DEFAULT NULL,
  `visit_date` datetime DEFAULT NULL,
  `area_style_areanm` varchar(255) DEFAULT NULL,
  `area_style_simcall` varchar(255) DEFAULT NULL,
  `detailed` varchar(255) DEFAULT NULL,
  `operators` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2781 DEFAULT CHARSET=utf8*/
}
