package com.data.migration.web.controller;

import com.data.migration.dto.MysqlConnDto;
import com.data.migration.service.api.IInfoSchemaService;
import com.data.migration.vo.ResponseVoResult;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("allTable")
    public ResponseVoResult qryAllTables(){
        try {
            List<String> tableNames = infoSchemaService.qryAllTableNames(MysqlConnDto.TYPE_MASTER);
            return ResponseVoResult.enumOK("查询成功",tableNames);
        } catch (SQLException e) {
            return ResponseVoResult.enumFailed("查询异常： " + ExceptionUtils.getFullStackTrace(e));
        }
    }

    @PostMapping("data")
    public ResponseVoResult migration(@RequestBody List<String> tableNames){
        tableNames.parallelStream().forEach(tableName -> {

        });
        return ResponseVoResult.enumOK("迁移成功");
    }

}
