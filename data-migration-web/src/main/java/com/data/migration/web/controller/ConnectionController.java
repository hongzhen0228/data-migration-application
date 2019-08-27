package com.data.migration.web.controller;

import com.data.migration.dto.MysqlConnDto;
import com.data.migration.service.api.IMysqlConnectionService;
import com.data.migration.vo.ResponseVoResult;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * 数据库连接测试
 */
@RestController("/conn")
public class ConnectionController {

    @Autowired
    private IMysqlConnectionService connectionService;


    @PostMapping("/master")
    public ResponseVoResult verifyMaster(@RequestParam(value = "url") String url,
                                         @RequestParam(value = "password") String password,
                                         @RequestParam(value = "username") String username,
                                         @RequestParam(value = "schema") String schema) {
        MysqlConnDto mysqlConnDto = new MysqlConnDto(url,username,password,schema,MysqlConnDto.TYPE_MASTER);
        try {
            return connectionService.verifyConnection(mysqlConnDto) ? ResponseVoResult.enumOK("连接成功")
                    : ResponseVoResult.enumFailed("连接失败");
        } catch (Exception e) {
            return ResponseVoResult.enumFailed("连接异常： " + ExceptionUtils.getFullStackTrace(e));
        }
    }

    @PostMapping("/host")
    public ResponseVoResult host(@RequestParam(value = "url") String url,
                                         @RequestParam(value = "password") String password,
                                         @RequestParam(value = "username") String username,
                                         @RequestParam(value = "schema") String schema) {
        MysqlConnDto mysqlConnDto = new MysqlConnDto(url,username,password,schema,MysqlConnDto.TYPE_HOST);
        try {
            return connectionService.verifyConnection(mysqlConnDto) ? ResponseVoResult.enumOK("连接成功")
                    : ResponseVoResult.enumFailed("连接失败");
        } catch (Exception e) {
            return ResponseVoResult.enumFailed("连接异常： " + ExceptionUtils.getFullStackTrace(e));
        }
    }

}
