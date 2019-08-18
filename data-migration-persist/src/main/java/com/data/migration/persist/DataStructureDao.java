package com.data.migration.persist;

import com.data.migration.dto.DataStructureDto;

import java.sql.SQLException;
import java.util.List;

public interface DataStructureDao {

    /**
     * 查询表结构
     * @param tableName
     * @return
     */
    List<DataStructureDto> qryDataStructure(String tableName,String type) throws SQLException;

}
