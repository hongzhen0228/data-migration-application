package com.data.migration.web.controller;

import com.data.migration.dto.DataContainer;
import com.data.migration.dto.MysqlConnDto;
import com.data.migration.service.api.ICreateTableService;
import com.data.migration.service.api.IDataMigrationService;
import com.data.migration.service.api.IInfoSchemaService;
import com.data.migration.vo.ResponseVoResult;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by hongzhen.cao on 2019/8/26.
 */
@RestController("/mig")
public class DataMigrationController {

    @Autowired
    private IInfoSchemaService infoSchemaService;
    @Autowired
    private ICreateTableService createTableService;
    @Autowired
    private IDataMigrationService dataMigrationService;

    @GetMapping("allTable")
    public ResponseVoResult qryAllTables(){
        try {
            List<String> tableNames = infoSchemaService.qryAllTableNames(MysqlConnDto.TYPE_MASTER);
            return ResponseVoResult.enumOK("查询成功",tableNames);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseVoResult.enumFailed("查询异常： " + ExceptionUtils.getFullStackTrace(e));
        }
    }

    @PostMapping("data")
    public ResponseVoResult migration(@RequestBody List<String> tableNames){
        try {
            if (!CollectionUtils.isEmpty(tableNames)) {
                for (String tableName : tableNames) {
                    String tableSql = createTableService.qryCreateTableSql(MysqlConnDto.TYPE_MASTER, tableName);
                    infoSchemaService.dropTable(MysqlConnDto.TYPE_HOST,tableName);
                    boolean createTable = createTableService.createTable(MysqlConnDto.TYPE_HOST, tableSql);
                    if (!createTable) {
                        return ResponseVoResult.enumFailed("数据库创建失败");
                    }
                    List<DataContainer> dataContainerList = dataMigrationService.qryByLimit(tableName, MysqlConnDto.TYPE_MASTER, 0, 10000);
                    if (!CollectionUtils.isEmpty(dataContainerList)) {
                        boolean insert = dataMigrationService.insert(tableName, MysqlConnDto.TYPE_HOST, dataContainerList);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVoResult.enumFailed("查询异常： " + ExceptionUtils.getFullStackTrace(e));
        }
        /*tableNames.parallelStream().forEach(tableName -> {
            try {
                String tableSql = createTableService.qryCreateTableSql(MysqlConnDto.TYPE_MASTER, tableName);
                boolean createTable = createTableService.createTable(MysqlConnDto.TYPE_HOST, tableSql);
                if (!createTable) {
                    return ResponseVoResult.enumFailed("数据库创建失败");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });*/
        return ResponseVoResult.enumOK("迁移成功");
    }

}
