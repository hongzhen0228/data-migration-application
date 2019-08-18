package com.data.migration.service.api;

import com.data.migration.dto.DataStructureDto;

import java.sql.SQLException;
import java.util.List;

public interface IDataStructureService {

    List<DataStructureDto> qryDataStructure(String tableName, String type) throws SQLException;
}
