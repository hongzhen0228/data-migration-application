package com.data.migration.persist.impl;

import com.data.migration.common.MysqlThreadHolder;
import com.data.migration.dto.DataStructureDto;
import com.data.migration.persist.DataStructureDao;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DataStructureDaoImpl implements DataStructureDao {
    @Override
    public List<DataStructureDto> qryDataStructure(String tableName, String type) throws SQLException {
        List<DataStructureDto> dataStructureDtoList = new ArrayList<>();
        Connection connection = MysqlThreadHolder.getConnection(type);
        Statement statement = connection.createStatement();
        String sql = "show COLUMNS from " + tableName;
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            DataStructureDto dataStructureDto = new DataStructureDto();
            dataStructureDto.setField(resultSet.getString(1));
            String typeInfo = resultSet.getString(2);
            dataStructureDto.setType(typeInfo.substring(0,typeInfo.indexOf("(")));
            dataStructureDto.setIsNull(resultSet.getString(3));
            dataStructureDto.setKey(resultSet.getString(4));
            dataStructureDto.setDefaultValue(resultSet.getString(5));
            dataStructureDtoList.add(dataStructureDto);
        }
        return dataStructureDtoList;
    }
}
