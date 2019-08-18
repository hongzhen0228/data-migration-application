package com.data.migration.dto;

import lombok.Data;

@Data
public class MysqlThreadData {

    private MysqlConnDto masterConnDto;

    private MysqlConnDto hostConnDto;
}
